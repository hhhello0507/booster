//
//  BoardCell.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem
import MyShared

struct BoardCell: View {
    
    private let board: BoardRes
    
    init(for board: BoardRes) {
        self.board = board
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(board.content)
                .myFont(.bodyM)
                .foreground(Colors.Label.normal)
            Text(board.createdAt.timeAgo)
                .myFont(.labelR)
                .foreground(Colors.Label.assistive)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(16)
        .foreground(Colors.Label.normal)
        .background(Colors.Background.normal)
        .cornerRadius(12, corners: .allCorners)
    }
}
