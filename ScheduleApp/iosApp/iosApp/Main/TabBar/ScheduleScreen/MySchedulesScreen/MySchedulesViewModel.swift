//
//  MySchedulesViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 10.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension MySchedulesView {
    
    @Observable
    class ViewModel {
        var project: Project?
        
        private var projects: [Project] = [
            Project(title: "Кейс №5891011121314", image: Image("Bird")),
            Project(title: "Кейс №6"),
        ]
        
        var sortedProjects: [Project] {
            get {
                projects.sorted { ($0.isPinned && !$1.isPinned) ||  ($0.title < $1.title)}
            }
            set {
                projects = newValue
            }
        }
        
        func save(project: Project) {
            if let index = projects.firstIndex(where: {$0.id == project.id}) {
                projects[index] = project
            } else {
                projects.append(project)
            }
        }
    }
}
