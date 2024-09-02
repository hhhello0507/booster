//
//  MainView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/2/24.
//

import SwiftUI
import MyDesignSystem

enum BoosterBottomItem: BottomAppBarItem {
    
    case home
    case exploration
    case my
    
    var icon: Iconable {
        switch self {
        case .home: Icons.Feature.Home
        case .exploration: Icons.ETC.Search
        case .my: Icons.Feature.Person
        }
    }
    
    var text: String {
        switch self {
        case .home: "홈"
        case .exploration: "탐색"
        case .my: "MY"
        }
    }
}

let data = [
    BoosterBottomItem.home,
    BoosterBottomItem.exploration,
    BoosterBottomItem.my
]

struct MainView: View {
    
    @StateObject private var explorationObservable = BoardObservable()
    @State private var selection = data[0]
    
    var body: some View {
        MyBottomAppBar(data, selection: selection) { item in
            self.selection = item
        } content: {
            switch selection {
            case .home: HomeView()
            case .exploration: ExplorationView()
            case .my: EmptyView()
            }
        }
        .environmentObject(explorationObservable)
        .onAppear {
            explorationObservable.fetchBoards()
        }
    }
}
