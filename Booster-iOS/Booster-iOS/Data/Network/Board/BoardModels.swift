//
//  AuthModels.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation

// MARK: - Response
struct BoardRes: Response {
    let id: Int
    let content: String
    let author: UserRes
    let createdAt: Date
}

// MARK: - Request
struct CreateBoardReq: Encodable {
    let content: String
}
