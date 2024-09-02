//
//  BoardsContainer.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem

struct BoardsContainer: View {
    
    private let columns: [GridItem] = [
        .init(.flexible())
    ]
    
    private let boards: [BoardRes]
    private var leftBoards: [BoardRes] {
        Array(boards[0..<(boards.count / 2)])
    }
    private var rightBoards: [BoardRes] {
        Array(boards[(boards.count / 2)..<boards.count])
    }
    
    init(for boards: [BoardRes]) {
        self.boards = boards
    }
    
    var body: some View {
        HStack(alignment: .top, spacing: 10) {
            LazyVGrid(columns: columns, spacing: 10) {
                ForEach(leftBoards, id: \.id) {
                    BoardCell(for: $0)
                }
            }
            LazyVGrid(columns: columns, spacing: 10) {
                ForEach(rightBoards, id: \.id) {
                    BoardCell(for: $0)
                }
            }
        }
    }
}
