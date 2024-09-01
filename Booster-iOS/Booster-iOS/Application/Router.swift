//
//  Router.swift
//  Graduating
//
//  Created by hhhello0507 on 8/20/24.
//

import SwiftUI

public final class Router: ObservableObject {
    @Published public var path = NavigationPath()
    
    public func push(_ view: any Hashable) {
        path.append(view)
    }
    
    public func pop() {
        path.removeLast()
    }
    
    public func toRoot() {
        path.removeLast(path.count)
    }
}
