//
//  AccountMenuViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import PhotosUI

extension AccountMenuView {
    @Observable
    class ViewModel {
        var name: String = ""
        var email: String = ""
        var avatarItem: PhotosPickerItem? {
            didSet {
                Task {
                    if let loaded = try? await avatarItem?.loadTransferable(type: Image.self) {
                        avatarImage = loaded
                    } else {
                        print("Failed")
                    }
                }
            }
        }
        var avatarImage: Image?
        
        func deleteAccount() {
            //MARK: CODE TO COMPLETE
        }
        
        func changePassword() {
            //MARK: CODE TO COMPLETE
        }
    }
}
