//
//  BasicsAddingViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

extension BasicsAddingView {
    
    class ViewModel {
        var navigationTitle: String
        var textFieldTitle: String
        var textViewTitle: String
        @Binding var title: String
        @Binding var description: String
        
        init(navigationTitle: String, textFieldTitle: String, textViewTitle: String, title: Binding<String>, description: Binding<String>) {
            self.navigationTitle = navigationTitle
            self.textFieldTitle = textFieldTitle
            self.textViewTitle = textViewTitle
            self._title = title
            self._description = description
        }
    }
}
