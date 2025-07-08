//
//  EyeButtonView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct EyeButtonView: View {
    @Binding var isShowingPassword: Bool
    
    var body: some View {
        Button {
            withAnimation {
                isShowingPassword.toggle()
            }
        } label: {
            Image(systemName: isShowingPassword ? "eye.slash" : "eye")
        }
        .foregroundStyle(.secondary)
    }
}
