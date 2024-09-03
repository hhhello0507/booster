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
    
    @AppState private var app
    @StateObject private var observable = SignInObservable()
    @StateObject private var appleSignInObservable = AppleSignInObservable()
    @EnvironmentObject private var dialog: DialogProvider
    
    var body: some View {
        VStack(spacing: 10) {
            Spacer()
            VStack(spacing: 4) {
                Image(.logo)
                    .resizable()
                    .renderingMode(.template)
                    .frame(width: 150, height: 150)
                    .foreground(Colors.Primary.normal)
                Image(.booster)
                    .renderingMode(.template)
                    .foreground(Colors.Primary.normal)
            }
            Spacer()
            AppleSignInButton {
                appleSignInObservable.signIn { idToken, nickname in
                    print(idToken)
                    observable.oAuth2SignIn(platformType: .apple, idToken: idToken, nickname: nickname) { token in
                        app.accessToken = token.accessToken
                        app.refreshToken = token.refreshToken
                    }
                } failureCompletion: {
                    handleSignInFailure()
                }
            }
            GoogleSignInButton {
                guard let presentingVC = UIApplicationUtil.rootViewController else {
                    handleSignInFailure()
                    return
                }
                GIDSignIn.sharedInstance.signIn(withPresenting: presentingVC) { result, err in
                    if let err {
                        print("sign in error - \(err)")
                        handleSignInFailure()
                        return
                    }
                    guard let result else {
                        print("result not founded")
                        handleSignInFailure()
                        return
                    }
                    let name = result.user.profile?.name ?? ""
                    guard let idToken = result.user.idToken?.tokenString else {
                        print("idToken not founded")
                        handleSignInFailure()
                        return
                    }
                    observable.oAuth2SignIn(platformType: .google, idToken: idToken, nickname: name) { token in
                        app.accessToken = token.accessToken
                        app.refreshToken = token.refreshToken
                    }
                }
            }
            .padding(.bottom, 24)
        }
        .padding(.horizontal, 15)
        .background(Colors.Background.neutral)
    }
    
    func handleSignInFailure() {
        dialog.present(
            .init(title: "로그인 실패")
        )
    }
}

#Preview {
    SignInView()
}
