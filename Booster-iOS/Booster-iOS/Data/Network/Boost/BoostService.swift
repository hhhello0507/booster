//
//  BoostService.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation
import Moya
import MyMoya

final class BoostService: Service<BoostEndpoint> {
    static let shared = BoostService(allowLog: true)
    
    func postBoost(_ req: PostBoostReq) -> ObservableResult<BaseVoidRes> {
        performRequest(.postBoost(req), res: BaseVoidRes.self).observe()
    }
}

enum BoostEndpoint: MyMoya.Endpoint {
    
    case postBoost(PostBoostReq)
    
    static var provider: MoyaProvider<Self> = .init(session: .init(interceptor: TokenInterceptor()))
    
    var host: String {
        "boost"
    }
    
    var route: (Moya.Method, String, Moya.Task) {
        switch self {
        case .postBoost(let req):
                .post - "" - req.toURLParameters()
        }
    }
}
