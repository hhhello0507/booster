//
//  AuthService.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation
import MyMoya
import Moya

final class AuthService: Service<AuthTarget> {
    
    static let shared = AuthService(allowLog: true)
    
    func oAuth2SignIn(_ req: OAuth2SignInReq) -> ObservableResult<BaseRes<TokenRes>> {
        performRequest(.signIn(req), res: BaseRes<TokenRes>.self).observe()
    }
    
    func refresh(_ req: RefreshReq) -> ObservableResult<BaseRes<TokenRes>> {
        performRequest(.refresh(req), res: BaseRes<TokenRes>.self).observe()
    }
}

enum AuthTarget: MyMoya.Endpoint {

    case signIn(OAuth2SignInReq)
    case refresh(RefreshReq)

    static var provider: MoyaProvider<Self> = .init()
    
    var host: String {
        "auth"
    }
    
    var route: (Moya.Method, String, Moya.Task) {
        switch self {
        case .signIn(let req):
                .post - "sign-in/oauth2" - req.toJSONParameters()
        case .refresh(let req):
                .post - "refresh" - req.toJSONParameters()
        }
    }
}
