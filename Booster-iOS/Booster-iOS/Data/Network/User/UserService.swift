//
//  UserService.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation
import Moya
import MyMoya

final class UserService: Service<UserEndpoint> {
    static let shared = UserService(allowLog: true)
    func getMe() -> ObservableResult<BaseRes<UserRes>> {
        performRequest(.me, res: BaseRes<UserRes>.self).observe()
    }
}

enum UserEndpoint: MyMoya.Endpoint {
    case me
    
    static var provider: MoyaProvider<Self> = .init(session: .init(interceptor: TokenInterceptor()))
    
    var host: String {
        "user"
    }
    
    var route: (Moya.Method, String, Moya.Task) {
        switch self {
        case .me:
                .get - "me" - .requestPlain
        }
    }
}
