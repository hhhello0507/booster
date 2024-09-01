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
    
    @StateObject private var observable = SignInObservable()
    
    var body: some View {
        MyTopAppBar.default(title: "로그인") { insets in
            VStack {
                Text("WOW")
                Spacer()
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
                        observable.googleSignIn(idToken: idToken)
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
