//
//  ChevroneViewModifier.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct ChevroneViewModifier: ViewModifier {
    @Environment(\.dismiss) var dismiss
   
    func body(content: Content) -> some View {
        content
            .navigationBarBackButtonHidden()
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button {
                        dismiss()
                    } label: {
                        Image(systemName: "chevron.backward")
                            .foregroundColor(.primary)
                    }
                }
            }
    }
}

extension View {
    func chevronBackButton() -> some View {
        modifier(ChevroneViewModifier())
    }
}
