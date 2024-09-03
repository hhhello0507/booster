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
    
    @AppState private var app
    @StateObject private var explorationObservable = BoardObservable()
    @State private var selection = data[0]
    
    var body: some View {
        MyBottomAppBar(data, selection: selection) { item in
            self.selection = item
        } content: {
            switch selection {
            case .home: HomeView()
            case .exploration: ExplorationView()
            case .my: MyView()
            }
        }
        .navigationDestination(for: MainDestination.self) { destination in
            switch destination {
            case .setting:
                SettingView()
            }
        }
        .environmentObject(explorationObservable)
        .onAppear {
            explorationObservable.fetchBoards()
            app.fetchUser()
        }
    }
}
