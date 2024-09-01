//
//  BaseObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Combine

open class BaseObservable: ObservableObject {
    public var subscriptions = Set<AnyCancellable>()
    
    deinit {
        subscriptions.forEach {
            $0.cancel()
        }
    }
}
