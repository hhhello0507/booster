//
//  AuthTest.swift
//  Auth-iOS
//
//  Created by hhhello0507 on 8/31/24.
//

import SwiftUI
import Alamofire

struct AuthTestView: View {
    var body: some View {
        VStack {
            Button("sign in") {
                Task {
                    do {
                        let response1 = try await AF.request(
                            "http://localhost:8080/auth/sign-up",
                            method: .post,
                            parameters: SignInReq(
                                username: "username",
                                password: "password",
                                passwordCheck: "password",
                                nickname: "nickname"
                            ),
                            encoder: JSONParameterEncoder()
                        ).serializingDecodable(BaseRes<JwtInfoRes>.self).value
                        
                        let response2 = try await AF.request(
                            "http://localhost:8080/test",
                            headers: ["Authorization": "Bearer \(response1.data.accessToken)"]
                        ).serializingDecodable(BaseRes<String>.self).value
                        print(response2)
                        
                        let response3 = try await AF.request(
                            "http://localhost:8080/auth/refresh",
                            method: .post,
                            parameters: RefreshReq(
                                refreshToken: response1.data.refreshToken
                            ),
                            encoder: JSONParameterEncoder()
                        ).serializingDecodable(BaseRes<JwtInfoRes>.self).value
                        print(response3)
                    } catch {
                        print(error)
                    }
                }
            }
        }
    }
}
