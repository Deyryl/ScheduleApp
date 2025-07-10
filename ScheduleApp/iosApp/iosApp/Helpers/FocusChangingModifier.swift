//
//  FieldsEnum.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

enum Fields: Hashable {
    case name
    case email
    case password
    case confirmPassword
    case task
    case tag
}

struct FocusViewModifier: ViewModifier {
    @FocusState var focusState: Fields?
    
    func body(content: Content) -> some View {
        content
            .toolbar {
                ToolbarItem(placement: .keyboard) {
                    Button("Готово") {
                        focusState = nil
                    }
                    .tint(.primary)
                    .frame(maxWidth: .infinity, alignment: .trailing)
                }
            }
    }
}

extension View {
    func addFocusToKeyboard(focusState: FocusState<Fields?>) -> some View {
        modifier(FocusViewModifier(focusState: focusState))
    }
}
