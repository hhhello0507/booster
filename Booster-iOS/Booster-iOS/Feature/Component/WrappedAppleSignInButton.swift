////
////  WrappedAppleSignInButton.swift
////  Booster-iOS
////
////  Created by hhhello0507 on 9/3/24.
////
//
//import SwiftUI
//import AuthenticationServices
//import MyDesignSystem
//import SnapKit
//
//public struct WrappedAppleSignInButton: UIViewControllerRepresentable {
//    
//    public typealias UIViewControllerType = AppleSignInViewController
//    
//    private let onSuccess: (_ token: String) -> Void
//    private let onFailure: () -> Void
//    
//    public init(
//        onSuccess: @escaping (_: String) -> Void,
//        onFailure: @escaping () -> Void
//    ) {
//        self.onSuccess = onSuccess
//        self.onFailure = onFailure
//    }
//    
//    public func makeUIViewController(context: Context) -> AppleSignInViewController {
//        let appleVC = AppleSignInViewController()
//        appleVC.onSuccess = onSuccess
//        appleVC.onFailure = onFailure
//        return appleVC
//    }
//    
//    public func updateUIViewController(_ uiViewController: AppleSignInViewController, context: Context) {
//        context.coordinator
//    }
//}
//
//
//public final class AppleSignInViewController: UIViewController {
//    
//    var onSuccess: ((_ token: String) -> Void)!
//    var onFailure: (() -> Void)!
//    var buttonLabel: UIHostingController<AppleSignInButton>!
//    
//    public override func viewDidLoad() {
//        super.viewDidLoad()
//        buttonLabel = UIHostingController(rootView: .init {
//            self.requestSignIn()
//        })
//        configureUI()
//        setUpLayout()
//        setUpAddTarget()
//    }
//    
//    private func configureUI() {
//        view.addSubview(buttonLabel.view)
//        addChild(buttonLabel)
//        buttonLabel.didMove(toParent: self)
//    }
//    
//    private func setUpLayout() {
//        buttonLabel.view.snp.makeConstraints {
//            $0.height.equalTo(56)
//            $0.leading.trailing.equalToSuperview()
//        }
//    }
//    
//    private func setUpAddTarget() {
////        buttonLabel.view.addTarget(self, action: #selector(requestSignIn), for: .touchUpInside)
//    }
//    
//    @objc
//    private func requestSignIn() {
//        let appleIDProvider = ASAuthorizationAppleIDProvider()
//        let request = appleIDProvider.createRequest()
//        request.requestedScopes = [.fullName, .email]
//        let authorizationController = ASAuthorizationController(authorizationRequests: [request])
//        authorizationController.delegate = self
//        authorizationController.presentationContextProvider = self
//        authorizationController.performRequests()
//    }
//}
//
//extension AppleSignInViewController: ASAuthorizationControllerDelegate, ASAuthorizationControllerPresentationContextProviding {
//    public func presentationAnchor(for controller: ASAuthorizationController) -> ASPresentationAnchor {
//        return self.view.window!
//    }
//    
//    public func authorizationController(controller: ASAuthorizationController, didCompleteWithAuthorization authorization: ASAuthorization) {
//        var isFailure = true
//        switch authorization.credential {
//        case let appleIDCredential as ASAuthorizationAppleIDCredential:
//            if let identityToken = appleIDCredential.identityToken,
//               let identifyTokenString = String(data: identityToken, encoding: .utf8) {
//                isFailure = false
//                onSuccess(identifyTokenString)
//                return
//            }
//        default:
//            break
//        }
//        if isFailure {
//            onFailure()
//        }
//    }
//    
//    public func authorizationController(controller: ASAuthorizationController, didCompleteWithError error: Error) {
//        print("login failed - \(error.localizedDescription)")
//        onFailure()
//    }
//}
