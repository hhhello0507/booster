//
//  SignInObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation

final class SignInObservable: BaseObservable<SignInObservable.Effect> {
    
    enum Effect {}
    
    func oAuth2SignIn(
        platformType: PlatformType,
        idToken: String,
        nickname: String,
        completion: @escaping (TokenRes) -> Void
    ) {
        AuthService.shared.oAuth2SignIn(
            .init(platformType: platformType, idToken: idToken, nickname: nickname)
        ).success { res in
            completion(res.data)
        }
        .observe(&subscriptions)
    }
}
