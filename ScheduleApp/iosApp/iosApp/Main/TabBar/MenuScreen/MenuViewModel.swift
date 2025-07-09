//
//  MenuViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

extension MenuView {
    
    class ViewModel: ObservableObject {
        @Published var toggle = false

        func setTheme() {
            UserDefaults.standard.set(toggle ? "dark" : "light", forKey: "theme")
        }

        func setupView() {
            toggle = (UserDefaults.standard.string(forKey: "theme") == "dark")
        }
    }

}
