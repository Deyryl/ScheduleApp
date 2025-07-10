//
//  BasicsAddingView.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct BasicsAddingView: View {
    var textFieldTitle: String
    var textViewTitle: String
    @Binding var title: String
    @Binding var description: String
    
    var body: some View {
        Group {
            Spacer()
                .frame(maxHeight: 20)
            
            VStack(alignment: .leading) {
                Text(textFieldTitle)
                    .font(.system(size: 12))
                    .foregroundStyle(.secondary)
                UnderScoredTextFieldView(name: "", text: $title)
                    .labelsHidden()
            }
            .accessibilityElement()
            .accessibilityLabel(textFieldTitle)
            .padding(.bottom, 20)
            
            VStack(alignment: .leading) {
                Text(textViewTitle)
                    .font(.system(size: 12))
                    .foregroundStyle(.secondary)
                
                UnderScoredTextFieldView(name: "", text: $description, axis: .vertical)
                    .lineLimit(2...)
            }
            .accessibilityElement()
            .accessibilityLabel(textViewTitle)
            .padding(.bottom, 20)
        }
    }
}
