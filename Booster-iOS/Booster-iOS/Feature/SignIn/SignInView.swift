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
    
    @EnvironmentObject private var appState: AppState
    @StateObject private var observable = SignInObservable()
    
    var body: some View {
        VStack {
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
            GoogleSignInButton {
                GIDSignIn.sharedInstance.signIn(withPresenting: rootViewController!) { result, err in
                    if let err {
                        print("sign in error - \(err)")
                        return
                    }
                    guard let result else {
                        print("result not founded")
                        return
                    }
                    guard let idToken = result.user.idToken?.tokenString else {
                        print("idToken not founded")
                        return
                    }
                    observable.googleSignIn(idToken: idToken) { token in
                        appState.accessToken = token.accessToken
                        appState.refreshToken = token.refreshToken
                    }
                }
            }
            .padding(.bottom, 24)
            .padding(.horizontal, 15)
        }
        .background(Colors.Background.neutral)
    }
}

#Preview {
    SignInView()
}
