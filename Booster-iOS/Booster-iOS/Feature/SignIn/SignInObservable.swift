//
//  SignInObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation

final class SignInObservable: BaseObservable {
    
    func googleSignIn(idToken: String) {
        AuthService.shared.oAuth2SignIn(
            .init(platformType: .google, idToken: idToken)
        ).success { res in
            print(res.data)
        }
        .observe(&subscriptions)
    }
}
