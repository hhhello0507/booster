//
//  Service.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Foundation
import MyMoya

extension Service {
    func performRequest<T: Decodable>(
        _ target: Target.Target,
        res: T.Type = BaseVoidRes.self
    ) -> Result<T> {
        performRequest(target, res: res, errorRes: ErrorRes.self)
    }
}
