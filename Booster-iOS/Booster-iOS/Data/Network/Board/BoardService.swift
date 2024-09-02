//
//  BoardService.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import MyMoya
import Moya

final class BoardService: Service<BoardEndpoint> {
    static let shared = BoardService(allowLog: true)
    func getAll(_ req: PageReq) -> ObservableResult<BaseRes<[BoardRes]>> {
        performRequest(.getAll(req), res: BaseRes<[BoardRes]>.self).observe()
    }
    
    func create(_ req: CreateBoardReq) -> ObservableResult<BaseRes<BoardRes>> {
        performRequest(.create(req), res: BaseRes<BoardRes>.self).observe()
    }
}

enum BoardEndpoint: MyMoya.Endpoint {
    case getAll(PageReq)
    case create(CreateBoardReq)
    
    static var provider: MoyaProvider<Self> = .init(session: .init(interceptor: TokenInterceptor()))
    
    var host: String {
        "board"
    }
    
    var route: (Moya.Method, String, Moya.Task) {
        switch self {
        case .getAll(let req):
                .get - "" - req.toURLParameters()
        case .create(let req):
                .post - "" - req.toJSONParameters()
        }
    }
}
