//
//  AppState.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/1/24.
//

import Foundation

final class AppState: ObservableObject {
    @Published var accessToken: String? = MySign.accessToken {
        didSet {
            if let accessToken {
                MySign.reissue(accessToken)
            }
        }
    }
}
