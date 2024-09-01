//
//  UserDefaultsType.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/1/24.
//

import Foundation
import MyShared
import SignKit

let user = UserDefaults(suiteName: "group.hhhello0507.booster") ?? .standard
public let MySign = Sign(store: .init(user))

enum UserDefaultsType: String, UserDefaultsProtocol {
    var userDefaults: UserDefaults {
        user
    }
    
    case accessToken = "ACCESS_TOKEN"
    case refreshToken = "REFRESH_TOKEN"
}
