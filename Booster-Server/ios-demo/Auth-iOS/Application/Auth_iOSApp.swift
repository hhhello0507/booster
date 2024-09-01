//
//  Auth_iOSApp.swift
//  Auth-iOS
//
//  Created by hhhello0507 on 8/29/24.
//

import SwiftUI

@main
struct Auth_iOSApp: App {
    var body: some Scene {
        WindowGroup {
            TabView {
                AuthTestView()
                    .tabItem { Text("auth") }
                OAuth2TestView()
                    .tabItem { Text("oauth2") }
            }
        }
    }
}
