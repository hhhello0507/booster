//
//  SettingView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/3/24.
//

import SwiftUI
import MyDesignSystem

struct SettingView: View {
    
    @AppState private var app
    @EnvironmentObject private var router: Router
    @EnvironmentObject private var dialogProvider: DialogProvider
    
    var body: some View {
        MyTopAppBar.small(title: "설정", background: Colors.Background.normal) { insets in
            ScrollView {
                LazyVStack(spacing: 10) {
                    MyRowView("로그아웃") {
                        dialogProvider.present(
                            .init(title: "정말 로그아웃하시겠습니까?")
                            .primaryButton("로그아웃") {
                                app.accessToken = nil
                                app.refreshToken = nil
                                router.toRoot()
                            }.secondaryButton("취소")
                        )
                    }
                }
                .padding(insets)
            }
        }
    }
}
