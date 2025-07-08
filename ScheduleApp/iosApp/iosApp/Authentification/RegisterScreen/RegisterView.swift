//
//  RegisterView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI


struct RegisterView: View {
    @State private var viewModel = ViewModel()
    @FocusState private var focusedField: Fields?
    
    var body: some View {
        VStack {
            AppTitleView()
                .padding(.bottom, 50)
            
            VStack(spacing: 30) {
                UnderScoredTextFieldView(name: "Имя", text: $viewModel.name)
                    .focused($focusedField, equals: .name)
                
                UnderScoredTextFieldView(name: "E-mail", text: $viewModel.email)
                    .focused($focusedField, equals: .email)
                
                Group {
                    if viewModel.isShowingPassword {
                        UnderScoredTextFieldView(name: "Пароль", text: $viewModel.password)
                    } else {
                        UnderScoredSecureTextFieldView(name: "Пароль", text: $viewModel.password)
                    }
                }
                .focused($focusedField, equals: .password)
                .overlay(alignment: .trailing) {
                    EyeButtonView(isShowingPassword: $viewModel.isShowingPassword)
                }
                
                Group {
                    if viewModel.isShowingConfirmPassword {
                        UnderScoredTextFieldView(name: "Повтор пароля", text: $viewModel.confirmPassword)
                    } else {
                        UnderScoredSecureTextFieldView(name: "Повтор пароля", text: $viewModel.confirmPassword)
                    }
                }
                .focused($focusedField, equals: .confirmPassword)
                .overlay(alignment: .trailing) {
                    EyeButtonView(isShowingPassword: $viewModel.isShowingConfirmPassword)
                }
            }
            .frame(maxWidth: 280)
            
            HStack {
                Button {
                    viewModel.gogogleRegistration()
                } label: {
                    Image("google-icon")
                }
                .buttonStyle(.plain)
                .padding(.trailing, 15)
                .accessibilityLabel("Логин через гугл")
                
                Button {
                    viewModel.vkRegistration()
                } label: {
                    Image("vk-icon")
                }
                .buttonStyle(.plain)
                .accessibilityLabel("Логин через вконтакте")
            }
            .padding(.vertical, 34)
            
            StrokedButtonView(name: "Создать") {
                viewModel.register {
                    viewModel.navigateToCode = true
                }
            }
            .navigationDestination(isPresented: $viewModel.navigateToCode){
                CodeAuthentificationView()
            }
        }
        .chevronBackButton()
        .addFocusToKeyboard(focusState: _focusedField)
    }
}

#Preview {
    RegisterView()
}
