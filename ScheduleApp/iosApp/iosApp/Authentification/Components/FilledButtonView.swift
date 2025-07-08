//
//  FilledButtonView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct FilledButtonView: View {
    var name: String
    var action: () -> Void
    
    var body: some View {
        Button {
            action()
        } label: {
            ZStack {
                RoundedRectangle(cornerRadius: 15)
                    .fill(.accent)
                    .frame(width: 270, height: 50)
                    .shadow(color: .primary.opacity(0.25), radius: 4, y: 1)
                
                Text(name)
                    .font(.system(size: 32))
                    .fontWeight(.semibold)
                    .foregroundStyle(.background)
            }
        }
        .buttonStyle(.plain)
    }
}

#Preview {
    FilledButtonView(name: "Войти") {
        
    }
}
