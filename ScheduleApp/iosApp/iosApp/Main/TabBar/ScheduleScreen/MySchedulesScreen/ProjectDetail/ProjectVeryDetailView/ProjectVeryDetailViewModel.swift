//
//  ProjectVeryDetailViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 12.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension ProjectVeryDetailView {
    @Observable
    class ViewModel {
        var image: Image
        var isShowingTag = false
        var users = [
            User(id: 1, image: Image("Bird"), name: "Кристина романова"),
            User(id: 2, image: Image("Bird"), name: "Кристина романова"),
            User(id: 3, image: Image("Bird"), name: "Кристина романова"),
        ]
        
        func addMemeber() {
            
        }
        
        init(image: Image? = Image(systemName: "photo")) {
            if let image {
                self.image = image
            } else {
                self.image = Image(systemName: "photo")
            }
        }
    }
}
