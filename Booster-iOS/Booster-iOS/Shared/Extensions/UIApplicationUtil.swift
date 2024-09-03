//
//  View+rootViewController.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/1/24.
//

import SwiftUI

final class UIApplicationUtil {

    static var rootViewController: UIViewController? {
        window?.rootViewController
    }

    static var window: UIWindow? {
        UIApplication.shared.connectedScenes
            .filter({ $0.activationState == .foregroundActive })
            .compactMap { $0 as? UIWindowScene }
            .compactMap { $0.keyWindow }
            .first
    }
}
