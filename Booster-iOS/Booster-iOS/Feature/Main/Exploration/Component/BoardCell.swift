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
            HStack {
                VStack(alignment: .leading, spacing: 0) {
                    Text(board.author.nickname)
                        .myFont(.labelM)
                        .foreground(Colors.Label.assistive)
                    Text(board.createdAt.timeAgo)
                        .myFont(.labelR)
                        .foreground(Colors.Label.assistive)
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                Text("\(board.boostCount)")
                    .myFont(.headlineM)
                    .foreground(Colors.Static.white)
                    .frame(width: 36, height: 36)
                    .background(
                        board.boosted
                        ? Colors.Primary.normal
                        : Colors.Line.normal
                    )
                    .clipShape(Circle())
            }
            .padding(.top, 6)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(16)
        .background(Colors.Background.normal)
        .cornerRadius(12, corners: .allCorners)
        .onTapGesture {
            if !board.boosted {
                action()
            }
        }
    }
}
