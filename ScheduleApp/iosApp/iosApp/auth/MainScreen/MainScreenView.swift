//
//  MainScreen.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MainScreen: View {
    
    var body: some View {
        NavigationStack {
            Spacer()
                .frame(maxHeight: 150)
            
            VStack {
                Text("Schedule")
                    .font(.system(size: 65))
                    .fontWeight(.medium)
                
                + Text("App")
                    .font(.system(size: 65))
                    .fontWeight(.medium)
                    .foregroundStyle(.accent)
            }
            .accessibilityHidden(true)
            
            //                .foregroundStyle(.ac)
            
            Spacer()
                .frame(maxHeight: 360)
            
            VStack {
                NavigationLink {
                    LoginView()
                } label: {
                    ZStack {
                        RoundedRectangle(cornerRadius: 10)
                            .fill(.accent)
                            .shadow(color: .black.opacity(0.25), radius: 4, y: 1)
                        
                        Text("Регистарция")
                            .font(.system(size: 32))
                            .fontWeight(.semibold)
                            .foregroundStyle(.white)
                    }
                    .frame(width: 344, height: 60)
                }
                .buttonStyle(.plain)
                .padding(.bottom, 20)
                .accessibilityRemoveTraits(.isLink)
                .accessibilityAddTraits(.isButton)
                
                
                NavigationLink {
                    RegisterView()
                } label: {
                    ZStack {
                        RoundedRectangle(cornerRadius: 10)
                            .fill(.white)
                            .shadow(color: .black.opacity(0.25), radius: 4, y: 1)
                            .overlay(
                                RoundedRectangle(cornerRadius: 10)
                                    .stroke(.accent, lineWidth: 2)
                            )
                        
                        Text("Вход")
                            .font(.system(size: 32))
                            .fontWeight(.semibold)
                            .foregroundStyle(.accent)
                    }
                    .frame(width: 344, height: 60)
                }
                .buttonStyle(.plain)
                .accessibilityRemoveTraits(.isLink)
                .accessibilityAddTraits(.isButton)
            }
            
            Spacer()
                .frame(maxHeight: 80)
        }
    }
}

#Preview {
    MainScreen()
}
