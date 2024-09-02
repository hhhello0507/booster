//
//  View+cardStyle.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem

extension View {
    func cardStyle() -> some View {
        self.padding(16)
            .background(Colors.Background.normal)
            .cornerRadius(18, corners: .allCorners)
    }
}
