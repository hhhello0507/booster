//
//  ExplorationObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation
import MyMoya

final class BoardObservable: BaseObservable<BoardObservable.Effect> {
    
    enum Effect {
        case refreshFailure
        case createBoardSuccess
        case createBoardFailure
    }
    
    @Published var boards: [BoardRes]? = nil
    
    @Published var creatingContent = false
    @Published var content = ""
    
    func fetchBoards() {
        BoardService.shared.getAll(
            .init(size: 30, page: 0)
        ).success { res in
            self.boards = res.data
        }.failure { err in
            if case .refreshFailure = err {
                self.emit(.refreshFailure)
            }
        }.observe(&subscriptions)
    }
    
    func createBoard() {
        BoardService.shared.create(
            .init(content: content)
        ).fetching {
            self.creatingContent = true
        }.success { res in
            self.emit(.createBoardSuccess)
            self.content = ""
            self.fetchBoards()
        }.failure { err in
            self.emit(.createBoardFailure)
        }.finished {
            self.creatingContent = false
        }.observe(&subscriptions)
    }
}
