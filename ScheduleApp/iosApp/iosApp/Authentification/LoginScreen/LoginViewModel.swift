//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension LoginView {
    @Observable
    class ViewModel {
        var email = ""
        var password = ""
        var isShowingPassword = false
        var isShowingChangePassword = false
        
        func login() {
            //MARK: CODE TO COMPLETE
            UserDefaults.standard.set(true, forKey: "UserLogged")
        }
        
        func googleLogin() {
            //MARK: CODE TO COMPLETE
            UserDefaults.standard.set(true, forKey: "UserLogged")
        }
        
        func vkLogin() {
            //MARK: CODE TO COMPLETE
            UserDefaults.standard.set(true, forKey: "UserLogged")
        }
        
        func changePassword() {
            isShowingChangePassword.toggle()
            //MARK: CODE TO COMPLETE
        }
    }
}
