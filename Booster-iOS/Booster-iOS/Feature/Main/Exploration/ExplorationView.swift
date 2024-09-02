//
//  ExplorationView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem

struct ExplorationView: View {
    var body: some View {
        MyTopAppBar.default(title: "탐색") {
                HStack(spacing: 0) {
                    Image(.logo)
                        .resizable()
                        .renderingMode(.template)
                        .foreground(Colors.Primary.normal)
                        .frame(width: 24, height: 24)
                    Text("100") // TODO: Dummy
                        .myFont(.bodyM)
                        .foreground(Colors.Label.assistive)
                }
                .padding(.trailing, 15)
        } content: { insets in
            ScrollView {
                LazyVGrid(columns: []) {
                    
                }
                .padding(insets)
            }
        }
    }
}

#Preview {
    ExplorationView()
}
