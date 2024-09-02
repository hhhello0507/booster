//
//  ExplorationObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation
import MyMoya

final class ExplorationObservable: BaseObservable<ExplorationObservable.Effect> {
    
    enum Effect {
        case refreshFailure
    }
    
    @Published var boards: [BoardRes]? = nil
    
    func fetchBoards() {
        BoardService.shared.getAll(
            .init(size: 30, page: 10)
        ).success { res in
            self.boards = res.data
        }.failure { err in
            if case .refreshFailure = err {
                self.emit(.refreshFailure)
            }
        }.observe(&subscriptions)
    }
}
