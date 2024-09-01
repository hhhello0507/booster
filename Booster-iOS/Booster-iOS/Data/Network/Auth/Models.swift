//
//  SignInReq.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation

// MARK: - Request
//struct SignInReq: Encodable {
//    let username: String
//    let password: String
//}
//
//struct SignUpReq: Encodable {
//    let username: String
//    let password: String
//    let passwordCheck: String
//    let nickname: String
//}

struct OAuth2SignIn: Encodable {
    let platformType: PlatformType
    let idToken: String
}

enum PlatformType: String, Encodable {
    case google = "GOOGLE"
}

struct RefreshReq: Encodable {
    let refreshToken: String
}

// MARK: - Response
struct TokenRes: Response {
    let accessToken: String
    let refreshToken: String
}
