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
        var id: UUID
        var title: String
        
        var image: Image?
        var photo: PhotosPickerItem? {
            didSet {
                Task {
                    if let loaded = try? await photo?.loadTransferable(type: Image.self) {
                        image = loaded
                    } else {
                        print("Failed")
                    }
                }
            }
        }
        var save: (Project) -> Void
        var maxTitleLength: Int = 15
        
        func saveProject() {
            //MARK: CODE TO COMPLETE
            let project = Project(id: id, title: title, image: image)
            save(project)
        }
        
        init (id: UUID, title: String, image: Image? = nil, save: @escaping (Project) -> Void) {
            self.id = id
            self.title = title
            self.save = save
            self.image = image
        }
    }
}
