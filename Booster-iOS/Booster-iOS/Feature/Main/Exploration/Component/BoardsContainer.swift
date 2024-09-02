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
        .init(.flexible()),
        .init(.flexible())
    ]
    
    private let boards: [BoardRes]
    
    init(for boards: [BoardRes]) {
        self.boards = boards
    }
    
    var body: some View {
        LazyVGrid(columns: columns) {
            ForEach(boards, id: \.id) {
                Text("\($0.content)")
                    .frame(minWidth: .infinity)
                    .foreground(Colors.Label.normal)
                    .background(Colors.Primary.normal)
            }
        }
    }
}
