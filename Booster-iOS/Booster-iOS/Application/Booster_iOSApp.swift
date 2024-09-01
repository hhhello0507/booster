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
    
    init() {
        Wanted.register()
    }
    
    var body: some Scene {
        WindowGroup {
            MyModalProvider(
                dialogProvider: dialogProvider,
                datePickerProvider: datePickerProvider,
                timePickerProvider: timePickerProvider
            ) {
                NavigationStack(path: $router.path) {
                    SignInView()
                }
            }
        }
    }
}
