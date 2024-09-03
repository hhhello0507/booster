//
//  AppleSignInObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/3/24.
//

import Foundation
import AuthenticationServices

final class AppleSignInObservable: NSObject, ObservableObject {
    
}

extension AppleSignInObservable: ASAuthorizationControllerPresentationContextProviding {
    func presentationAnchor(for controller: ASAuthorizationController) -> ASPresentationAnchor {
        
    }
}
