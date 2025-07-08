//
//  RegisterViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

extension RegisterView {
    
    @Observable
    class ViewModel {
        var name = ""
        var email = ""
        var password = ""
        var confirmPassword = ""
        var isShowingPassword = false
        var isShowingConfirmPassword = false
        var navigateToCode = false
        
        func gogogleRegistration() {
            //MARK: CODE TO COMPLETE
        }
        
        func vkRegistration() {
            //MARK: CODE TO COMPLETE
        }
        
        func register(completion: @escaping () -> Void) {
            //MARK: CODE TO COMPLETE
            completion()
        }
    }
}
