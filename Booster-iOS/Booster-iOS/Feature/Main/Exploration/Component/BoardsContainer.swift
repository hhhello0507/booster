//
//  BoardsContainer.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem

struct BoardsContainer: View {
    
    enum Action {
        case cell(BoardRes)
    }
    
    private let columns: [GridItem] = [
        .init(.flexible())
    ]
    
    private let boards: [BoardRes]
    private let action: (Action) -> Void
    
    private var leftBoards: [BoardRes] {
        Array(boards[0..<(boards.count / 2)])
    }
    private var rightBoards: [BoardRes] {
        Array(boards[(boards.count / 2)..<boards.count])
    }
    
    init(
        for boards: [BoardRes],
        action: @escaping (Action) -> Void
    ) {
        self.boards = boards
        self.action = action
    }
    
    var body: some View {
        HStack(alignment: .top, spacing: 10) {
            makeBoards(data: leftBoards)
            makeBoards(data: rightBoards)
        }
    }
    
    func makeBoards(data boards: [BoardRes]) -> some View {
        LazyVStack(spacing: 10) {
            ForEach(boards, id: \.id) { board in
                BoardCell(for: board) {
                    action(.cell(board))
                }
            }
        }
    }
}
