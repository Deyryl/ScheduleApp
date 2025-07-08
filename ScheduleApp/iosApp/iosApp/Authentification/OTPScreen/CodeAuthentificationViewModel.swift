//
//  OTPViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

extension CodeAuthentificationView {
    
    @Observable
    class ViewModel {
        var timer = Timer.publish(every: 1, on: .main, in: .common).autoconnect()
        var remainTime = 90
        let codeLength = 4
        var code: String = ""
        var serverCodeResponse: String = ""
        
        var showSendCodeButton: Bool {
            remainTime == 0
        }
        
        
        func checkCode(result: String) -> TypingState {
            if result.count < codeLength {
                return .typing
            } else if result == serverCodeResponse {
                defer {
                    UserDefaults.standard.set(true, forKey: "UserLogged")
                }
                return .valid
            } else {
                return .invalid
            }
        }
        
        func fetchServerCode() async {
            //MARK: CODE TO COMPLETE
            serverCodeResponse = "1234"
            remainTime = 90
            timer = Timer.publish(every: 1, on: .main, in: .common).autoconnect()
        }
    }
}
