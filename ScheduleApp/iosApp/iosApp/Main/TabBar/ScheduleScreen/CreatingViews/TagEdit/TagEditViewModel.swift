//
//  TagEditViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension TagEditView {
    @Observable
    class ViewModel {
        var tagTitle: String
        var description: String
        var color: Color
        var save: (Tag) -> Void
        
        func saveTag() {
            //MARK: CODE TO COMPLETE
            
            let tag = Tag(title: tagTitle, description: description, color: color)
            save(tag)
        }
        
        
        init(tag: Tag, save: @escaping (Tag) -> Void) {
            self.tagTitle = tag.title
            self.description = tag.description
            self.color = tag.color
            self.save = save
        }
        
        init(save: @escaping (Tag) -> Void) {
            self.tagTitle = ""
            self.description = ""
            self.color = Color(.white)
            self.save = save
        }
    }
}
