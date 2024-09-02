//
//  MainView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/1/24.
//

import SwiftUI
import MyDesignSystem
import MyShared

struct HomeView: View {
    
    var body: some View {
        MyTopAppBar.default(
            title: "홈"
        ) { insets in
            ScrollView {
                VStack(spacing: 10) {
                    MyCardView(title: "부스터") {
                        HStack {
                            Image(.boosterIcon)
                                .resizable()
                                .renderingMode(.template)
                                .foreground(Colors.Primary.normal)
                                .frame(width: 32, height: 32)
                                .padding(2)
                                .background(Colors.Fill.normal)
                                .clipShape(Circle())
                            Text("100")
                                .myFont(.headlineB)
//                                .foreground(Colors.)
                        }
                    }
                }
                .padding(insets)
            }
        }
    }
}

#Preview {
    HomeView()
}
