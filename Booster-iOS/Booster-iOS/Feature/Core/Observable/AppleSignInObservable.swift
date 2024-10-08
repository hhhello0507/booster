//
//  AppleSignInObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/3/24.
//

import Combine
import AuthenticationServices
import Then

final class AppleSignInObservable: NSObject, ObservableObject {
    
    @Published var error: Error?
    
    private var successCompletion: ((String) -> Void)?
    private var failureCompletion: (() -> Void)?
    
    func signIn(
        successCompletion: @escaping (String) -> Void,
        failureCompletion: @escaping () -> Void
    ) {
        self.successCompletion = successCompletion
        self.failureCompletion = failureCompletion
        
        let request = ASAuthorizationAppleIDProvider().createRequest().then {
            $0.requestedScopes = [.fullName, .email]
        }
//        request.nonce = encodeNonce
        
        ASAuthorizationController(authorizationRequests: [request]).do {
            $0.delegate = self
            $0.presentationContextProvider = self
            $0.performRequests()
        }
    }
}

extension AppleSignInObservable: ASAuthorizationControllerDelegate {
    
    func authorizationController(controller: ASAuthorizationController, didCompleteWithAuthorization authorization: ASAuthorization) {
        guard let credential = authorization.credential as? ASAuthorizationAppleIDCredential else {
            print("AppleSignInObservable - credential not found")
            failureCompletion?()
            return
        }
        guard let code = credential.authorizationCode,
              let codeString = String(data: code, encoding: .utf8) else {
            print("AppleSignInObservable - identityToken not found")
            failureCompletion?()
            return
        }
        
        successCompletion?(codeString)
    }
    
    func authorizationController(controller: ASAuthorizationController, didCompleteWithError error: any Error) {
        self.error = error
        print("AppleSignInObservable - failure \(error)")
        failureCompletion?()
    }
}

extension AppleSignInObservable: ASAuthorizationControllerPresentationContextProviding {
    func presentationAnchor(for controller: ASAuthorizationController) -> ASPresentationAnchor {
        guard let window = UIApplicationUtil.window else {
            fatalError("No window found")
        }
        return window
    }
}
