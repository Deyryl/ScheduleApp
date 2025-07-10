//
//  TagEditView.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

//MARK: CODE TO COMPLETE
struct TagEditView: View {
    @State private var viewModel: ViewModel
    @Environment(\.dismiss) var dismiss
    @FocusState private var focusField: Fields?
    
    var body: some View {
        NavigationStack {
            VStack(alignment: .leading) {
                Text("Тег")
                    .font(.largeTitle)
                    .fontWeight(.semibold)
                
                BasicsAddingView(textFieldTitle: "Название тега", textViewTitle: "Описание", title: $viewModel.tagTitle, description: $viewModel.description)
                    .focused($focusField, equals: .tag)

                
                VStack(alignment: .leading) {
                    Text("Выбор цвета")
                        .font(.system(size: 12))
                        .foregroundStyle(.secondary)
                        .labelsHidden()
                    
                    ColorPicker("Выбор цвета", selection: $viewModel.color, supportsOpacity: false)
                        .labelsHidden()
                        .scaleEffect(CGSize(width: 4, height: 4))
                        .font(.largeTitle)
                        .frame(width: 120, height: 120)
                }
                
                Spacer()
            }
            .padding(.horizontal, 20)
            .toolbar {
                ToolbarItem(placement: .confirmationAction) {
                    Button("Сохранить") {
                        viewModel.saveTag()
                        dismiss()
                    }
                }
                
                ToolbarItem(placement: .cancellationAction) {
                    Button("Отменить") {
                        dismiss()
                    }
                }
            }
            .tint(.accent)
        }
    }
    
    init(tag: Tag, save:  @escaping (Tag) -> Void) {
        self.viewModel = ViewModel(tag: tag, save: save)
    }
}

#Preview {
    var tag = Tag(title: "", description: "", color: Color.white)
    TagEditView(tag: tag) {
        tag = $0
    }
}
