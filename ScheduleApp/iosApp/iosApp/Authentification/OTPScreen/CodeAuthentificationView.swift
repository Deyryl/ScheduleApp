//
//  OTPView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct CodeAuthentificationView: View {
    @State private var viewModel = ViewModel()
    
    var body: some View {
        VStack {
            
            AppTitleView()
                .padding(.bottom, 40)
            
            Text("Введите код из письма, присланного на example@gmail.com ")
                .font(.system(size: 20))
                .foregroundStyle(.secondary)
                .tint(.secondary)
                .frame(maxWidth: 300, maxHeight: 72)
                .multilineTextAlignment(.center)
            
            VerificationField(length: viewModel.codeLength, value: $viewModel.code, onChange: viewModel.checkCode)
                .padding(.vertical, 40)
                
            Group {
                if viewModel.showSendCodeButton {
                    Button("Отправить код повторно") {
                        Task {
                            await viewModel.fetchServerCode()
                        }
                    }
                    .buttonStyle(.plain)
                    .foregroundStyle(.accent)
                    .transition(.scale)
                } else {
                    Text("Отправить повторно: \(viewModel.remainTime) сек.")
                        .foregroundStyle(.secondary)
                        .transition(.scale)
                }
            }
            .font(.system(size: 16))
            
            
            Spacer()
                .frame(maxHeight: 170)
        }
        .task {
            await viewModel.fetchServerCode()
        }
        .onReceive(viewModel.timer) { _ in
            if !viewModel.showSendCodeButton {
                withAnimation {
                    viewModel.remainTime -= 1
                }
            } else {
                viewModel.timer.upstream.connect().cancel()
            }
        }
        .animation(.default, value: viewModel.showSendCodeButton)
        .chevronBackButton()
        
    }
}

#Preview {
    CodeAuthentificationView()
}
