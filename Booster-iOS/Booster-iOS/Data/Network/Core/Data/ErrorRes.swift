//
//  ErrorRes.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation
import MyMoya

public struct ErrorRes: ErrorResponse {
    let status: Int
    let message: String
}
