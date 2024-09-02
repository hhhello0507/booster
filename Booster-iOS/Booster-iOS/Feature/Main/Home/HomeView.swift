//
//  MainView.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/1/24.
//

import SwiftUI
import MyDesignSystem
import MyShared

struct HomeView: View {
    
    @EnvironmentObject private var dialogProvider: DialogProvider
    @EnvironmentObject private var observable: BoardObservable
    @FocusState private var contentFocus
    
    var body: some View {
        MyTopAppBar.default(
            title: "홈"
        ) { insets in
            VStack(spacing: 10) {
                Button {
                    
                } label: {
                    HStack(spacing: 4) {
                        Image(.boosterIcon)
                            .resizable()
                            .renderingMode(.template)
                            .foreground(Colors.Primary.normal)
                            .frame(width: 28, height: 28)
                            .padding(2)
                            .clipShape(Circle())
                        Text("100")
                            .myFont(.headlineR)
                            .foreground(Colors.Label.assistive)
                        Spacer()
                    }
                    .background(Colors.Background.normal)
                }
                .scaledButton()
                .cardStyle()
                VStack(alignment: .leading, spacing: 15) {
                    Text("Today")
                        .font(.wanted(.Bold, size: 36))
                    HStack(spacing: 8) {
                        TextField("여기에 작성", text: $observable.content)
                            .tint(Colors.Primary.normal)
                            .focused($contentFocus)
                        let disabled = observable.creatingContent
                        Button {
                            observable.createBoard()
                        } label: {
                            let opacity = if disabled {
                                0.5
                            } else if observable.content.isEmpty {
                                0.0
                            } else {
                                1.0
                            }
                            Image(icon: Icons.Arrow.ExpandArrow)
                                .resizable()
                                .renderingMode(.template)
                                .rotationEffect(.degrees(180))
                                .foreground(Colors.Static.white)
                                .frame(width: 24, height: 24)
                                .padding(8)
                                .background(Colors.Primary.normal)
                                .clipShape(Circle())
                                .opacity(opacity)
                        }
                        .disabled(disabled)
                        .scaledButton()
                    }
                }
                .frame(maxWidth: .infinity)
                .padding(.vertical, 16)
                .cardStyle()
                .onTapGesture {
                    contentFocus = true
                }
                Spacer()
            }
            .padding(insets)
        }
        .onAppear {
            observable.subscribe { effect in
                switch effect {
                case .createBoardSuccess:
                    dialogProvider.present(
                        .init(title: "작성 성공 ⚡️")
                    )
                case .createBoardFailure:
                    break
                default:
                    break
                }
            }
        }
    }
}

#Preview {
    HomeView()
}
