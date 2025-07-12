//
//  ProjectDetailView.swift
//  iosApp
//
//  Created by Никита Арабчик on 12.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct ProjectDetailView: View {
    @Environment(\.dismiss) var dismiss
    @Binding var project: Project
    @State private var viewModel: ViewModel
    
    var body: some View {
        VStack {
            HStack {
                HStack(alignment: .center) {
                    Button {
                        dismiss()
                    } label : {
                        Image(systemName: "chevron.left")
                    }
                    .buttonStyle(.plain)
                    .foregroundStyle(.secondary)
                    
                    HStack {
                        viewModel.image
                            .resizable()
                            .scaledToFit()
                            .frame(width: 50, height: 50)
                        
                        Text(project.title)
                            .font(.system(size: 24))
                        
                        Image(systemName: project.isSounded ? "speaker" : "speaker.slash")
                            .foregroundStyle(.secondary)
                            .contentTransition(.symbolEffect(.replace))
                    }
                    .onTapGesture {
                        viewModel.isSelected.toggle()
                    }
                }
                
                Spacer()
                
                Menu("", systemImage: "ellipsis") {
                    
                    Button(project.isPinned ? "Открепить" : "Закрепить", systemImage: project.isPinned ? "pin" : "pin.slash") {
                        
                        project.isPinned.toggle()
                        
                    }
                    
                    Button("\(project.isSounded ? "Выкл." : "Вкл." ) уведомления", systemImage: project.isSounded ? "speaker" : "speaker.slash") {
                        withAnimation {
                            project.isSounded.toggle()
                        }
                    }
                    
                    Button("Выйти", systemImage: "door.left.hand.open", role: .destructive) {
                        //MARK: CODE TO COMPLETE
                    }
                }
                .padding(.bottom, 15)
                .font(.system(size: 24))
                .accessibilityLabel("Действия")
                .menuStyle(.borderlessButton)
                .tint(.accent)
            }
            .padding(.horizontal, 20)
            RoundedRectangle(cornerRadius: 2)
                .fill(.accent)
                .frame(height: 2)
            
            ScrollView {
                //MARK: CODE TO COMPLETE
                ForEach(0..<viewModel.sortedTasks.count) { index in
                    if index == 0 {
                        DateView(date: viewModel.sortedTasks[index].startDate ?? viewModel.sortedTasks[index].endDate!)
                        TaskCardView(task: viewModel.sortedTasks[index])
                    } else if (viewModel.sortedTasks[index].startDate ?? viewModel.sortedTasks[index].endDate!) != (viewModel.sortedTasks[index - 1].startDate ?? viewModel.sortedTasks[index - 1].endDate!){
                        DateView(date: viewModel.sortedTasks[index].startDate ?? viewModel.sortedTasks[index].endDate!)
                        TaskCardView(task: viewModel.sortedTasks[index])
                    } else {
                        TaskCardView(task: viewModel.sortedTasks[index])
                    }
                }
            }
        }
        .overlay(alignment: .bottomTrailing) {
            Button {
                viewModel.isAdding.toggle()
            } label: {
                Image(systemName: "plus.circle.fill")
            }
            .accessibilityLabel("Добавить задачу")
            .font(.system(size: 50))
            .padding(20)
            .padding(.bottom, 20)
            .tint(.accent)
        }
        .fullScreenCover(isPresented: $viewModel.isSelected) {
            ProjectVeryDetailView(project: $project)
        }
        .fullScreenCover(isPresented: $viewModel.isAdding) {
            TaskEditView()
        }
    }
    
    init(project: Binding<Project>) {
        self._project = project
        self.viewModel = ViewModel(image: project.image.wrappedValue)
    }
}

struct DateView: View {
    var date: Date
    
    var body: some View {
        HStack {
            Text(formattedRelativeDate(date))
                .font(.system(size: 24))
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.horizontal, 20)
        }
        Divider()
            .frame(height: 1)
            .background(.accent)
            .padding(.top, -10)
    }
    
    func formattedRelativeDate(_ date: Date) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "ru_RU")
        dateFormatter.dateStyle = .long
        dateFormatter.timeStyle = .none
        dateFormatter.doesRelativeDateFormatting = true
        
        var result = ""
        
        if Calendar.current.isDateInToday(date) ||
            Calendar.current.isDateInTomorrow(date) ||
            Calendar.current.isDateInYesterday(date) {
            result += dateFormatter.string(from: date) + ", "
        }
        
        if Calendar.current.date(byAdding: .year, value: 1, to: Date())! < date {
            dateFormatter.doesRelativeDateFormatting = false
            dateFormatter.dateFormat = "d MMMM, yyyy"
            result += dateFormatter.string(from: date)
        } else {
            dateFormatter.dateFormat = "d MMMM"
            result += dateFormatter.string(from: date)
        }
        
        return result
    }
}

#Preview {
    @Previewable @State var project = Project(title: "Кейс №5", image: Image("Bird"))
    ProjectDetailView(project: $project)
}
