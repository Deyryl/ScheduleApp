//
//  AccountMenuView.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import PhotosUI

struct AccountMenuView: View {
    @State private var viewModel = ViewModel()
    @FocusState private var field: Fields?
    
    var body: some View {
        VStack(spacing: 40) {
            HStack(alignment: .bottom) {
                VStack(alignment: .leading) {
                    Text("Имя")
                        .font(.system(size: 12))
                        .foregroundStyle(.secondary)
                    UnderScoredTextFieldView(name: "Имя", text: $viewModel.name)
                        .focused($field, equals: .name)
                }
                
                PhotosPicker(selection: $viewModel.avatarItem) {
                    if let image = viewModel.avatarImage {
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(width: 100, height: 100)
                            .clipShape(.rect(cornerRadius: 10))
                    } else {
                        ContentUnavailableView {
                            Image(systemName: "photo.badge.plus")
                                .font(.system(size: 60))
                            Text("No Picture")
                                .font(.system(size: 16))
                        }
                        .frame(width: 120, height: 120)
                    }
                }
                .foregroundStyle(.secondary)
                .buttonStyle(.plain)
                
            }
            
            VStack(alignment: .leading) {
                Text("Email")
                    .font(.system(size: 12))
                    .foregroundStyle(.secondary)
                UnderScoredTextFieldView(name: "Email", text: $viewModel.email)
                    .focused($field, equals: .email)
            }
            
            HStack {
                VStack(alignment: .leading) {
                    Text("Email")
                        .font(.system(size: 12))
                        .foregroundStyle(.secondary)
                    Button("Сменить пароль") {
                        viewModel.changePassword()
                    }
                    .buttonStyle(.borderedProminent)
                }
                
                Spacer()
            }
            
            Spacer()
            
            Button("Удалить", role: .destructive) {
                viewModel.deleteAccount()
            }
            .buttonStyle(.borderedProminent)
            .tint(.red)
        }
        .navigationTitle("Аккаунт")
        .padding(.horizontal, 20)
        .tint(.accent)
    }
}

#Preview {
    NavigationStack {
        AccountMenuView()
    }
}
