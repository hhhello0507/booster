//
//  ArrowIcon.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/3/24.
//

import SwiftUI
import MyDesignSystem

struct ArrowIcon: View {
    
    enum Direction {
        case start
        case end
        
        var degrees: Double {
            switch self {
            case .start: 0
            case .end: 180
            }
        }
    }
    
    private let direction: Direction
    
    init(_ direction: Direction) {
        self.direction = direction
    }
    
    var body: some View {
        Image(icon: Icons.Arrow.ExpandArrow)
            .resizable()
            .renderingMode(.template)
            .foreground(Colors.Label.assistive)
            .frame(width: 18, height: 18)
            .rotationEffect(.degrees(direction.degrees))
    }
}
