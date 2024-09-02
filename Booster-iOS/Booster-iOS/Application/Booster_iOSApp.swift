//
//  Booster_iOSApp.swift
//  Booster-iOS
//
//  Created by hhhello0507 on 9/1/24.
//

import SwiftUI
import MyDesignSystem

@main
struct Booster_iOSApp: App {
    
    @State private var dialogProvider = DialogProvider()
    @State private var timePickerProvider = TimePickerProvider()
    @State private var datePickerProvider = DatePickerProvider()
    @StateObject private var router = Router()
    @StateObject private var app = AppObservable()
    
    init() {
        Wanted.register()
        initColor()
    }
    
    var body: some Scene {
        WindowGroup {
            MyModalProvider(
                dialogProvider: dialogProvider,
                datePickerProvider: datePickerProvider,
                timePickerProvider: timePickerProvider
            ) {
                NavigationStack(path: $router.path) {
                    if app.accessToken != nil {
                        MainView()
                    } else {
                        SignInView()
                    }
                }
            }
            .environmentObject(app)
        }
    }
    
    func initColor() {
        CustomPalette.primary99 = Color(0xFFFFFAFA)
        CustomPalette.primary95 = Color(0xFFFFE8E5)
        CustomPalette.primary90 = Color(0xFFFFD2CC)
        CustomPalette.primary80 = Color(0xFFFFA599)
        CustomPalette.primary70 = Color(0xFFFF7866)
        CustomPalette.primary60 = Color(0xFFFF6652)
        CustomPalette.primary50 = Color(0xFFFF6652)
        CustomPalette.primary40 = Color(0xFFFF6652)
        CustomPalette.primary30 = Color(0xFFFF6652)
        CustomPalette.primary20 = Color(0xFFFF6652)
        CustomPalette.primary10 = Color(0xFFFF6652)
    }
}
