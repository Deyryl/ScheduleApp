//
//  MySchedulesViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 10.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct Project: Identifiable {
    var id = UUID()
    var title: String
    var image: Image?
    var isPinned: Bool = false
    var isSounded: Bool = false
}

extension MySchedulesView {
    
    @Observable
    class ViewModel {
        var project: Project?
        
        var projects: [Project] = [
            Project(title: "Кейс №5891011121314", image: Image("Bird")),
            Project(title: "Кейс №6"),
        ]
        
        func save(project: Project) {
            
        }
    }
}
