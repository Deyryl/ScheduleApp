//
//  LoginView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct LoginView: View {
    @State private var viewModel = ViewModel()
    @FocusState var focusedField: Fields?
    
    var body: some View {
        VStack {
            AppTitleView()
                .padding(.bottom, 80)
            
            VStack {
                UnderScoredTextFieldView(name: "Электронная почта", text: $viewModel.email)
                    .frame(maxWidth: 280)
                    .padding(.bottom, 30)
                    .focused($focusedField, equals: .email)
                
                Group {
                    if viewModel.isShowingPassword {
                        UnderScoredTextFieldView(name: "Пароль", text: $viewModel.password)
                    } else {
                        UnderScoredSecureTextFieldView(name: "Пароль", text: $viewModel.password)
                    }
                }
                .focused($focusedField, equals: .password)
                .frame(maxWidth: 280)
                .overlay(alignment: .trailing) {
                    EyeButtonView(isShowingPassword: $viewModel.isShowingPassword)
                        .accessibilityLabel("Показать пароль")
                }
            }
            .padding(.bottom, 60)
            
            HStack {
                Button {
                    viewModel.googleLogin()
                } label: {
                    Image("google-icon")
                }
                .buttonStyle(.plain)
                .padding(.trailing, 15)
                .accessibilityLabel("Логин через гугл")
                
                Button {
                    viewModel.vkLogin()
                } label: {
                    Image("vk-icon")
                }
                .buttonStyle(.plain)
                .accessibilityLabel("Логин через вконтакте")
            }
            .padding(.bottom, 40)
            
            FilledButtonView(name: "Войти", action: viewModel.login)
                .padding(.bottom, 20)
            
            Button("Забыли пароль?", action: viewModel.changePassword)
                .font(.system(size: 20))
                .fontWeight(.light)
                .buttonStyle(.plain)
                .foregroundStyle(.secondary)
        }
        .chevronBackButton()
        .addFocusToKeyboard(focusState: _focusedField)
        .fullScreenCover(isPresented: $viewModel.isShowingChangePassword) {
            ForgetPasswordView()
        }
    }
}

#Preview { 
    LoginView()
}
