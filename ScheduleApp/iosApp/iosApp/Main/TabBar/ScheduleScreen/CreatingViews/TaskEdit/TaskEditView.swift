//
//  TaskEditView.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct TaskEditView: View {
    @State private var viewModel = ViewModel()
    @Environment(\.dismiss) var dismiss
    
    var body: some View {
        NavigationStack {
            VStack(alignment: .leading) {
                BasicsAddingView(navigationTitle: "Задача🥹", textFieldTitle: "Название задачи", textViewTitle: "Описание", title: $viewModel.title, description: $viewModel.description)
                
                VStack(alignment: .leading) {
                    Text("Время")
                        .font(.system(size: 12))
                        .foregroundStyle(.secondary)
                    Picker("Диапазон", selection: $viewModel.timeType) {
                        ForEach(TimeType.allCases, id: \.self) { type in
                            Text(type.rawValue)
                                .tag(type)
                        }
                    }
                    .pickerStyle(.segmented)
                }
                .accessibilityElement()
                .accessibilityLabel("Тип времени")
                .accessibilityHint(viewModel.timeType.rawValue)
                
                VStack(spacing: 15) {
                    if  viewModel.timeType == .deadline{
                        DatePicker("Выберите конечную дату",
                                   selection: $viewModel.endDate.withDefault(),
                                   in: Date()...,
                                   displayedComponents: [.date, .hourAndMinute]
                        )
                        .foregroundStyle(.accent)
                        .labelsHidden()
                    } else if viewModel.timeType == .endsAt {
                        DatePicker("Выберите начальную дату",
                                   selection: $viewModel.startDate.withDefault(),
                                   in: Date()...,
                                   displayedComponents: [.date, .hourAndMinute]
                        )
                        .foregroundStyle(.accent)
                        .labelsHidden()
                    } else {
                        DatePicker("Выберите начальную дату",
                                   selection: $viewModel.startDate.withDefault(),
                                   in: Date()...,
                                   displayedComponents: [.date, .hourAndMinute]
                        )
                        .foregroundStyle(.accent)
                        .labelsHidden()
                        DatePicker("Выберите конечную дату",
                                   selection: $viewModel.endDate.withDefault(),
                                   in: (viewModel.startDate ?? Date())...,
                                   displayedComponents: [.date, .hourAndMinute]
                        )
                        .foregroundStyle(.accent)
                        .labelsHidden()
                    }
                }
                .padding(.vertical, 20)
                
                VStack(alignment: .leading) {
                    Text("Теги")
                        .font(.system(size: 12))
                        .foregroundStyle(.secondary)
                    
                    ForEach(viewModel.tags) { tag in
                        TagInTaskView(tag: tag)
                    }
                }
                
                Button("Добавить тег +") {
                    viewModel.tag = Tag(title: "", description: "", color: Color.white)
                }
                .frame(maxWidth: .infinity, alignment: .center)
                
                Spacer()
            }
            .padding(.horizontal, 20)
            .toolbar {
                ToolbarItem(placement: .confirmationAction) {
                    Button("Сохранить") {
                        viewModel.saveTask()
                        dismiss()
                    }
                }
                
                ToolbarItem(placement: .cancellationAction) {
                    Button("Отменить", role: .cancel) {
                        dismiss()
                    }
                }
            }
            .fullScreenCover(item: $viewModel.tag) { tag in
                TagEditView(tag: tag) {
                    viewModel.tags.append($0)
                }
            }
        }
        .tint(.accent)
    }
}

struct TagInTaskView: View {
    var tag: Tag
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Rectangle()
                    .fill(tag.color)
                    .frame(width: 20, height: 20)
                
                Text(tag.title)
                    .foregroundStyle(tag.color)
                    .frame(width: 100, alignment: .leading)
                
                Text(tag.description)
                    .frame(width: .infinity, height: 40, alignment: .leading)
                    .foregroundStyle(.secondary)
                    .multilineTextAlignment(.leading)
                
            }
            .font(.system(size: 16))
            
            Divider()
                .frame(width: .infinity, height: 1)
                .background(Color.accent)
        }
    }
}
#Preview {
    TaskEditView()
}
