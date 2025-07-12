//
//  ProjectEditView.swift
//  iosApp
//
//  Created by Никита Арабчик on 10.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import PhotosUI

struct ProjectEditView: View {
    @Environment(\.dismiss) var dismiss
    @State private var viewModel: ViewModel
    @FocusState private var field: Fields?
    
    var body: some View {
        NavigationStack {
            VStack(alignment: .leading) {
                Text("Проект")
                    .font(.largeTitle)
                    .fontWeight(.semibold)
                
                Spacer()
                    .frame(height: 20)
                
                VStack {
                    PhotosPicker(selection: $viewModel.photo) {
                        if let image = viewModel.image {
                            image
                                .resizable()
                                .scaledToFill()
                                .frame(width: 250, height: 250)
                                .clipped()
                        } else {
                            VStack {
                                Image(systemName: "photo.badge.plus")
                                    .font(.system(size: 150))
                                Text("No Picture")
                                    .font(.system(size: 60))
                            }
                            .foregroundStyle(.secondary)
                            .frame(maxWidth: 300, maxHeight: 300)
                            .overlay {
                                RoundedRectangle(cornerRadius: 10)
                                    .stroke(.secondary, lineWidth: 2)
                            }
                        }
                    }
                    .buttonStyle(.plain)
                }
                .frame(maxWidth: .infinity)
                .padding(.bottom, 40)
                
                VStack(alignment: .leading) {
                    Text("Название")
                        .font(.system(size: 12))
                        .foregroundStyle(.secondary)
                        .labelsHidden()
                    UnderScoredTextFieldView(name: "Название", text: $viewModel.title)
                        .focused($field, equals: .name)
                        .onChange(of: viewModel.title) {
                            if viewModel.title.count > viewModel.maxTitleLength {
                                viewModel.title = String(viewModel.title.prefix(viewModel.maxTitleLength))
                            }
                        }
                }
                
                Spacer()
            }
            .padding(.horizontal, 20)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Отмена", role: .cancel) {
                        dismiss()
                    }
                }
                
                ToolbarItem(placement: .confirmationAction) {
                    Button("Сохранить", role: .cancel) {
                        viewModel.saveProject()
                        dismiss()
                    }
                }
            }
        }
        .addFocusToKeyboard(focusState: _field)
    }
    
    init (project: Project, save: @escaping (Project) -> Void) {
        viewModel = ViewModel(id: project.id, title: project.title, image: project.image, save: save)
    }
}

#Preview {
    ProjectEditView(project: Project(title: "")) { _ in
        
    }
}
