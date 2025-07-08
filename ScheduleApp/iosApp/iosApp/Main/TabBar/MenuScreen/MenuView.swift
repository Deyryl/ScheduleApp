//
//  MenuView.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct CornerRotateModifier: ViewModifier {
    let amount: Double
    let scale: Double
    
    func body(content: Content) -> some View {
        content
            .rotation3DEffect(.degrees(amount), axis: (x: 1, y: 0, z: 0))
            .scaleEffect(scale)
    }
}

extension AnyTransition {
    static var scaleRotating: AnyTransition {
        .modifier(
            active: CornerRotateModifier(amount: 270, scale: 0),
            identity: CornerRotateModifier(amount: 0, scale: 1)
        )
    }
}

struct MenuView: View {
    @Environment(\.colorScheme) var colorScheme
    @State private var toggle = false
    
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
                                Group {
                                    if toggle {
                                        Image(systemName: "moon")
                                            .resizable()
                                            .scaledToFit()
                                    } else {
                                        Image(systemName: "sun.lefthalf.filled")
                                            .resizable()
                                            .scaledToFit()
                                    }
                                }
                                .frame(width: 26, height: 26)
                                .foregroundStyle(.accent)
                                .padding(.leading, 6)
                                .padding(.trailing, -4)
                                .transition(.scaleRotating)
                                
                                Spacer()
                                
                                Toggle("Смена темы", isOn: $toggle.animation(.easeInOut(duration: 0.5)))
//                                    .labelsHidden()
                                    .font(.system(size: 22))
                                    .padding(.horizontal, 10)
                            }
                        }
                        .padding(.horizontal, 20)
                    }
                }
                .buttonStyle(.plain)
                
                Spacer()
            }
            .navigationTitle("Меню")
            .listStyle(.plain)
            .onChange(of: toggle) {
                withAnimation {
                    UserDefaults.standard.set(toggle ? "dark" : "light", forKey: "theme")
                }
            }
            .onAppear {
                toggle = UserDefaults.standard.string(forKey: "theme") == "dark"
            }
        }
    }
}

#Preview {
    MenuView()
}
