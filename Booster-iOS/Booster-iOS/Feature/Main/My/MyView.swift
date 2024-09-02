//
//  MyView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem

struct MyView: View {
    var body: some View {
        MyTopAppBar.default(title: "MY") { insets in
            ScrollView {
                VStack(spacing: 10) {
                    MyRowView("") {
                        
                    }
                }
                .padding(insets)
            }
        }
    }
}
