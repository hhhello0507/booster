//
//  SignInObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation

final class SignInObservable: BaseObservable<SignInObservable.Effect> {
    
    enum Effect {}
    
    func googleSignIn(idToken: String, completion: @escaping (TokenRes) -> Void) {
        AuthService.shared.oAuth2SignIn(
            .init(platformType: .google, idToken: idToken)
        ).success { res in
            completion(res.data)
        }
        .observe(&subscriptions)
    }
}
