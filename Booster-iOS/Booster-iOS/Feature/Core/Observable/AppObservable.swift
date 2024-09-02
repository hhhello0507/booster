//
//  AppState.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/1/24.
//

import Foundation
import SwiftUI

@propertyWrapper
struct AppState: DynamicProperty {
    
    @EnvironmentObject private var appObservable: AppObservable
    
    var wrappedValue: AppObservable {
        appObservable
    }
}

final class AppObservable: BaseObservable<AppObservable.Effect> {
    enum Effect {}
    
    @Published var accessToken: String? = MySign.accessToken {
        didSet {
            if let accessToken {
                MySign.reissue(accessToken)
            }
        }
    }
    @Published var refreshToken: String? = MySign.refreshToken {
        didSet {
            if let refreshToken, let accessToken {
                MySign.login(id: "", password: "", accessToken: accessToken, refreshToken: refreshToken)
            }
        }
    }
    
    @Published var user: UserRes?
    
    func fetchUser() {
        UserService.shared.getMe()
            .success { res in
                self.user = res.data
            }
            .observe(&subscriptions)
    }
}
