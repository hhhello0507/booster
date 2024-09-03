//
//  MyView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem

struct MyView: View {
    
    @AppState private var app
    @EnvironmentObject private var router: Router
    
    var body: some View {
        MyTopAppBar.default(
            title: "MY",
            buttons: [
                .init(icon: Icons.Feature.Setting) {
                    router.push(MainDestination.setting)
                }
            ]
        ) { insets in
            ScrollView {
                LazyVStack(spacing: 10) {
                    if let user = app.user {
                        MyCardView(title: "프로필") {
                            VStack(spacing: 4) {
                                HStack(spacing: 8) {
                                    Text("이름")
                                        .myFont(.bodyB)
                                        .foreground(Colors.Label.assistive)
                                    Text(user.nickname)
                                        .myFont(.bodyM)
                                        .foreground(Colors.Label.alternative)
                                    Spacer()
                                }
                            }
                            .padding(6)
                        }
                        MyCardView(title: "부스터") {
                            HStack(spacing: 4) {
                                Image(.boosterIcon)
                                    .resizable()
                                    .renderingMode(.template)
                                    .foreground(Colors.Primary.normal)
                                    .frame(width: 28, height: 28)
                                    .padding(2)
                                    .clipShape(Circle())
                                Text("\(user.booster)")
                                    .myFont(.headlineR)
                                    .foreground(Colors.Label.assistive)
                                Spacer()
                            }
                        }
                    }
                }
                .padding(insets)
            }
        }
    }
}
