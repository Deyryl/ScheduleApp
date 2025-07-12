//
//  ProjectModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 10.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct Project: Identifiable, Equatable {
    var id = UUID()
    var title: String
    var image: Image?
    var isPinned: Bool = false
    var isSounded: Bool = false
    
    static func == (lhs: Project, rhs: Project) -> Bool {
        lhs.id == rhs.id &&
        lhs.title == rhs.title &&
        lhs.image == rhs.image &&
        lhs.isPinned == rhs.isPinned &&
        lhs.isSounded == rhs.isSounded
    }
}
