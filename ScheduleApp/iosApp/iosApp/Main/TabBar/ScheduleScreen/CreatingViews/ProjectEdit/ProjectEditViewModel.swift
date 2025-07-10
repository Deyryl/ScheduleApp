//
//  projectEditViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 10.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import PhotosUI

extension ProjectEditView {
    
    @Observable
    class ViewModel {
        var title: String
        var image: Image?
        var photo: PhotosPickerItem?
        
        init (title: String, image: Image? = nil, photo: PhotosPickerItem? = nil) {
            self.title = title
            self.image = image
            self.photo = photo
        }
    }
}
