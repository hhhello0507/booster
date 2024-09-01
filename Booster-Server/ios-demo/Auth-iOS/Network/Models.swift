//
//  Models.swift
//  Auth-iOS
//
//  Created by hhhello0507 on 8/31/24.
//

import Foundation

// MARK: - Request
struct SignInReq: Encodable {
    let username: String
    let password: String
    let passwordCheck: String
    let nickname: String
}

struct RefreshReq: Encodable {
    let refreshToken: String
}

struct OAuth2SignInReq: Encodable {
    let platformType: String
    let idToken: String
}

// MARK: - Response
struct JwtInfoRes: Decodable {
    let accessToken: String
    let refreshToken: String
}

struct BaseRes<T: Decodable>: Decodable {
    let status: Int
    let message: String
    let data: T
}
