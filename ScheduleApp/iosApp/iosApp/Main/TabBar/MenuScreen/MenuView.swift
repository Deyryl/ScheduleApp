//
//  MenuView.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MenuView: View {
    @Namespace private var iconAnimation
    @StateObject private var viewModel = ViewModel()
    
    var body: some View {
        NavigationStack {
            VStack {
                VStack {
                    NavigationLink {
                        AccountMenuView()
                    } label: {
                        MenuItemView("Аккаунт", systemImage: "person.crop.square")
                    }
                    
                    NavigationLink {
                        InfoMenuView()
                    } label: {
                        MenuItemView("Информация", systemImage: "info.circle")
                    }
                    
                    HStack {
                        ZStack {
                            RoundedRectangle(cornerRadius: 10)
                                .fill(.background)
                                .shadow(color: .primary.opacity(0.25) ,radius: 4, y: 1)
                                .frame(maxWidth: .infinity, maxHeight: 44)
                            
                            HStack {
                                Image(systemName: viewModel.toggle ? "moon" : "sun.max.fill")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 26, height: 26)
                                    .foregroundStyle(.accent)
                                    .matchedGeometryEffect(id: "icon", in: iconAnimation)
                                    .rotationEffect(.degrees(viewModel.toggle ? 360 : 0))
                                    .animation(.easeInOut(duration: 0.5), value: viewModel.toggle)
                                
                                Spacer()
                                
                                Toggle("Смена темы", isOn: $viewModel.toggle)
                                    .font(.system(size: 22))
                                    .padding(.horizontal, 10)
                                    .padding(.leading, -4)
                            }
                            .padding(.leading, 6)
                        }
                        .padding(.horizontal, 20)
                    }
                }
                .buttonStyle(.plain)
                
                Spacer()
            }
            .navigationTitle("Меню")
            .listStyle(.plain)
            .onChange(of: viewModel.toggle) {
                withAnimation(.easeInOut(duration: 0.5)) {
                    viewModel.setTheme()
                }
            }
            .onAppear {
                viewModel.setupView()
            }
        }
    }
}

#Preview {
    MenuView()
}
