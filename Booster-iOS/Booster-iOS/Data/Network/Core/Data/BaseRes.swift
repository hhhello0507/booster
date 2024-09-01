//
//  BaseRes.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation

struct BaseRes<T: Response>: Response {
    let status: Int
    let message: String
    let data: T
}
