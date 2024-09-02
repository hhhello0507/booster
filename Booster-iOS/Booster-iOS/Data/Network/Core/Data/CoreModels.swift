//
//  CoreModels.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation
import MyMoya

// MARK: - Response
struct BaseRes<T: Response>: Response {
    let status: Int
    let message: String
    let data: T
}

struct BaseVoidRes: Response {
    let status: Int
    let message: String
}

public struct ErrorRes: ErrorResponse {
    let status: Int
    let message: String
}

// MARK: - Request
struct PageReq: Encodable {
    let size: Int
    let page: Int
}
