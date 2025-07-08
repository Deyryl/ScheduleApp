//
//  ForgetPasswordViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension ForgetPasswordView {
    @Observable
    class ViewModel {
        var isShowingPassword = false
        var isShowingConfirmPassword = false
        var password = ""
        var confirmPassword = ""
        
        func changePassword(dismiss: DismissAction) {
            //MARK: CODE TO COMPLETE
            dismiss()
        }
    }
}
