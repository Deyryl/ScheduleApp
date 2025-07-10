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
    @FocusState private var focusField: Fields?
    
    var body: some View {
        NavigationStack {
            VStack(alignment: .leading) {
                Text("Задача🥹")
                    .font(.largeTitle)
                    .fontWeight(.semibold)
                ScrollView {
                    BasicsAddingView(textFieldTitle: "Название задачи", textViewTitle: "Описание", title: $viewModel.title, description: $viewModel.description)
                        .focused($focusField, equals: .task)
                    
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
                        
                        if viewModel.timeType == .endsAt ||
                            viewModel.timeType == .range{
                            DatePicker("Выберите начальную дату",
                                       selection: $viewModel.startDate.withDefault(),
                                       in: Date()...,
                                       displayedComponents: [.date, .hourAndMinute]
                            )
                            .foregroundStyle(.accent)
                            .labelsHidden()
                            .transition(.opacity)
                        }
                        
                        if  viewModel.timeType == .deadline || viewModel.timeType == .range {
                            DatePicker("Выберите конечную дату",
                                       selection: $viewModel.endDate.withDefault(),
                                       in: Date()...,
                                       displayedComponents: [.date, .hourAndMinute]
                            )
                            .foregroundStyle(.accent)
                            .labelsHidden()
                            .transition(.opacity)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.vertical, 10)
                    
                    VStack(alignment: .leading) {
                        Text("Теги")
                            .font(.system(size: 12))
                            .foregroundStyle(.secondary)
                        
                        List {
                            ForEach(viewModel.tags) { tag in
                                TagInTaskView(tag: tag)
                                    .listRowSeparator(.hidden)
                                    .swipeActions(edge: .trailing) {
                                        Button("Удалить", systemImage: "trash", role: .destructive) {
                                            viewModel.tags.removeAll(where: { $0.id == tag.id })
                                        }
                                        .tint(.red)
                                    }
                                    .onTapGesture {
                                        viewModel.tag = tag
                                    }
                            }
                        }
                        .frame(height: CGFloat((viewModel.tags.count * 65) + (viewModel.tags.count < 4 ? 200 : 0)), alignment: .top)
                        .listStyle(.inset)
                    }
                    Spacer()
                }
            }
            .animation(.easeInOut, value: viewModel.timeType)
            .padding(.horizontal, 20)
            .addFocusToKeyboard(focusState: _focusField)
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
                TagEditView(tag: tag) { savingTag in
                    viewModel.saveTag(savingTag: savingTag)
                }
            }
            .overlay(alignment: .bottom) {
                Button("Добавить тег +") {
                    viewModel.tag = Tag(title: "", description: "", color: Color.white)
                }
                .buttonStyle(.borderedProminent)
            }
        }
        .tint(.accent)
    }
}

#Preview {
    TaskEditView()
}
