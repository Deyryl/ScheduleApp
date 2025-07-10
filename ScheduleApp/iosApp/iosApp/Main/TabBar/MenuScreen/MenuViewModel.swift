//
//  MenuViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension MenuView {
    
    class ViewModel: ObservableObject {
        @Published var toggle = UserDefaults.standard.string(forKey: "theme") == "dark"
        @Published var path = NavigationPath()

        func setTheme() {
            UserDefaults.standard.set(toggle ? "dark" : "light", forKey: "theme")
        }
    }

}
