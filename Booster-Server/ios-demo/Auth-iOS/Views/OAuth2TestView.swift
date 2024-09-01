import SwiftUI
import GoogleSignIn
import GoogleSignInSwift
import Alamofire



struct OAuth2TestView: View {
    var body: some View {
        VStack {
            GoogleSignInButton {
                GIDSignIn.sharedInstance.signIn(withPresenting: rootViewController!) { signInResult, error in
                    if let error {
                        print(error)
                    }
                    if let signInResult {
                        dump(signInResult)
                    }
                    guard let idToken = signInResult?.user.idToken?.tokenString else {
                        print("idToken not founded")
                        return
                    }
                    
                    Task {
                        do {
                            let response1 = try await API.session.request(
                                "http://localhost:8080/auth/sign-in/oauth2",
                                method: .post,
                                parameters: OAuth2SignInReq(
                                    platformType: "GOOGLE",
                                    idToken: idToken
                                ),
                                encoder: JSONParameterEncoder()
                            ).serializingDecodable(BaseRes<JwtInfoRes>.self).value
                            print(response1)
                        } catch {
                            print(error)
                        }
                    }
                }
            }
        }
    }
}

extension OAuth2TestView {
    var rootViewController: UIViewController? {
        UIApplication.shared.connectedScenes
            .filter({ $0.activationState == .foregroundActive })
            .compactMap { $0 as? UIWindowScene }
            .compactMap { $0.keyWindow }
            .first?.rootViewController
    }
}
