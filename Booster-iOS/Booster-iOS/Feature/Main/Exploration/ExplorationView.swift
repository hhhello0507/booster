//
//  ExplorationView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem

struct ExplorationView: View {
    
    @AppState private var app
    @EnvironmentObject private var observable: BoardObservable
    
    var body: some View {
        MyTopAppBar.default(title: "탐색") {
                HStack(spacing: 0) {
                    Image(.logo)
                        .resizable()
                        .renderingMode(.template)
                        .foreground(Colors.Primary.normal)
                        .frame(width: 24, height: 24)
                    if let boostCount = app.user?.boostCount {
                        Text("\(boostCount)")
                            .myFont(.bodyM)
                            .foreground(Colors.Label.assistive)
                    }
                }
                .padding(.trailing, 15)
        } content: { insets in
            ScrollView {
                Group {
                    if let boards = observable.boards {
                        BoardsContainer(for: boards)
                            .padding(.bottom, 32)
                    } else {
                        ProgressView()
                    }
                }
                .padding(insets)
            }
        }
    }
}

#Preview {
    ExplorationView()
}
