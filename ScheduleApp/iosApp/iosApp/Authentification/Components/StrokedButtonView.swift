//
//  StrokedButtonView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct StrokedButtonView: View {
    var name: String
    var action: () -> Void
    
    var body: some View {
        Button {
            action()
        } label: {
            ZStack {
                RoundedRectangle(cornerRadius: 10)
                    .fill(.background)
                    .shadow(color: .primary.opacity(0.25), radius: 4, y: 1)
                    .overlay(
                        RoundedRectangle(cornerRadius: 10)
                            .stroke(.accent, lineWidth: 2)
                    )
                    .frame(width: 270, height: 50)
                
                Text(name)
                    .font(.system(size: 32))
                    .fontWeight(.semibold)
                    .foregroundStyle(.accent)
            }
        }
    }
}

