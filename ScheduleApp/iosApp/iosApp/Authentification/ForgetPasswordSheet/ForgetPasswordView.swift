//
//  ForgetPasswordView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct ForgetPasswordView: View {
    @Environment(\.dismiss) var dismiss
    @State private var viewModel = ViewModel()
    @FocusState private var field: Fields?
    
    var body: some View {
        NavigationStack {
            VStack {
                AppTitleView()
                    .padding(.bottom, 100)
                VStack {
                    Group {
                        if viewModel.isShowingPassword {
                            UnderScoredTextFieldView(name: "Новый пароль", text: $viewModel.password)
                        } else {
                            UnderScoredSecureTextFieldView(name: "Новый пароль", text: $viewModel.password)
                        }
                    }
                    .focused($field, equals: .password)
                    .overlay(alignment: .trailing) {
                        EyeButtonView(isShowingPassword: $viewModel.isShowingPassword)
                    }
                    .padding(.bottom, 30)
                    
                    Group {
                        if viewModel.isShowingConfirmPassword { 
                            UnderScoredTextFieldView(name: "Повтор пароля", text: $viewModel.confirmPassword)
                        } else {
                            UnderScoredSecureTextFieldView(name: "Повтор пароля", text: $viewModel.confirmPassword)
                        }
                    }
                    .focused($field, equals: .confirmPassword)
                    .overlay(alignment: .trailing) {
                        EyeButtonView(isShowingPassword: $viewModel.isShowingConfirmPassword)
                    }
                }
                .frame(maxWidth: 280)
                .padding(.bottom, 160)
                
                FilledButtonView(name: "Сменить") {
                    viewModel.changePassword(dismiss: dismiss)
                }
            }
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Отменить") {
                        dismiss()
                    }
                    .buttonStyle(.plain)
                    .foregroundStyle(.accent)
                }
            }
        }
    }
}

#Preview {
    ForgetPasswordView()
}
