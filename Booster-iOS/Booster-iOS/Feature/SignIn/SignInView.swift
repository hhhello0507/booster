//
//  SignInView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/1/24.
//

import SwiftUI
import MyDesignSystem
import GoogleSignIn
import GoogleSignInSwift

struct SignInView: View {
    
    var body: some View {
        MyTopAppBar.default(title: "로그인") { insets in
            VStack {
                Text("WOW")
                GoogleSignInButton {
                    GIDSignIn.sharedInstance.signIn(withPresenting: rootViewController!) { result, err in
                        if let err {
                            print(err)
                        }
                        guard let result else {
                            print("result not founded")
                            return
                        }
                        guard let idToken = result.user.idToken?.tokenString else {
                            print("idToken not founded")
                            return
                        }
                        
                        Task {
                            //                                do {
                            //                                    let response1 = try await API.session.request(
                            //                                        "http://localhost:8080/auth/sign-in/oauth2",
                            //                                        method: .post,
                            //                                        parameters: OAuth2SignInReq(
                            //                                            platformType: "GOOGLE",
                            //                                            idToken: idToken
                            //                                        ),
                            //                                        encoder: JSONParameterEncoder()
                            //                                    ).serializingDecodable(BaseRes<JwtInfoRes>.self).value
                            //                                    print(response1)
                            //                                } catch {
                            //                                    print(error)
                            //                                }
                        }
                    }
                }
            }
            .padding(insets)
        }
    }
}

#Preview {
    SignInView()
}
