//
//  BasicsAddingView.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct BasicsAddingView: View {
    @State private var viewModel: ViewModel
    @FocusState private var focusField: Fields?
    
    var body: some View {
        Group {
            Text(viewModel.navigationTitle)
                .font(.largeTitle)
                .fontWeight(.semibold)
            
            Spacer()
                .frame(maxHeight: 20)
            
            VStack(alignment: .leading) {
                Text(viewModel.textFieldTitle)
                    .font(.system(size: 12))
                    .foregroundStyle(.secondary)
                UnderScoredTextFieldView(name: "", text: $viewModel.title)
                    .labelsHidden()
                    .focused($focusField, equals: .title)
            }
            .accessibilityElement()
            .accessibilityLabel(viewModel.textFieldTitle)
            .padding(.bottom, 20)
            
            VStack(alignment: .leading) {
                Text(viewModel.textViewTitle)
                    .font(.system(size: 12))
                    .foregroundStyle(.secondary)
                
                UnderScoredTextFieldView(name: "", text: $viewModel.description, axis: .vertical)
                    .lineLimit(2...)
                    .focused($focusField, equals: .description)
            }
            .accessibilityElement()
            .accessibilityLabel(viewModel.textViewTitle)
            .padding(.bottom, 20)
        }
        .addFocusToKeyboard(focusState: _focusField)
    }
    
    init(navigationTitle: String, textFieldTitle: String, textViewTitle: String, title: Binding<String>, description: Binding<String>) {
        self.viewModel = ViewModel(navigationTitle: navigationTitle, textFieldTitle: textFieldTitle, textViewTitle: textViewTitle, title: title, description: description)
    }
}
