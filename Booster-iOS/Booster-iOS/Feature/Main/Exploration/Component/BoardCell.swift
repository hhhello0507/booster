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
    private let action: () -> Void
    
    init(
        for board: BoardRes,
        action: @escaping () -> Void
    ) {
        self.board = board
        self.action = action
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
        .if(board.boosted) {
            $0.stroke(0, color: Colors.Label.normal)
        }
        .onTapGesture {
            action()
        }
    }
}
