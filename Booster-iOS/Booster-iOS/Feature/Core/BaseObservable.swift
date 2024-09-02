//
//  BaseObservable.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import Combine

open class BaseObservable<Effect>: ObservableObject {
    public var subscriptions = Set<AnyCancellable>()
    private let effect = PassthroughSubject<Effect, Never>()
    
    func subscribe(_ subscriber: @escaping (Effect) -> Void) {
        effect
            .sink(receiveValue: subscriber)
            .store(in: &subscriptions)
    }
    
    func emit(_ effect: Effect) {
        self.effect.send(effect)
    }
    
    deinit {
        subscriptions.forEach {
            $0.cancel()
        }
    }
}
