//
//  ProjectDetailView.swift
//  iosApp
//
//  Created by Никита Арабчик on 12.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct ProjectVeryDetailView: View {
    @Environment(\.dismiss) var dismiss
    @Binding var project: Project
    @State private var viewModel: ViewModel
    
    var body: some View {
        VStack(alignment: .center) {
            HStack(alignment: .center) {
                HStack {
                    Button {
                        dismiss()
                    } label : {
                        Image(systemName: "chevron.left")
                    }
                    .buttonStyle(.plain)
                    .foregroundStyle(.secondary)
                    
                    viewModel.image
                        .resizable()
                        .scaledToFill()
                        .frame(width: 50, height: 50)
                    
                    Text(project.title)
                        .font(.system(size: 24))
                    
                    Image(systemName: project.isSounded ? "speaker" : "speaker.slash")
                        .foregroundStyle(.secondary)
                        .contentTransition(.symbolEffect(.replace))
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
                .accessibilityLabel("Действия")
                .menuStyle(.borderlessButton)
                .tint(.accent)
                .font(.system(size: 24))
            }
            .padding(.horizontal, 20)
            RoundedRectangle(cornerRadius: 2)
                .fill(.accent)
                .frame(height: 2)
            //MARK: CODE TO COMPLETE
//            if user.id == ownerid {
                Button("Добавить участника", systemImage: "person.badge.plus") {
                    viewModel.addMemeber()
                }
                .font(.system(size: 26))
                .tint(.accent)
                .padding(.top, 5)
//            }
            
            ProjectUsersView(users: $viewModel.users, ownerid: 1)
            
            Picker("", selection: $viewModel.isShowingTag) {
                Text("Теги")
                    .tag(true)
                Text("История")
                    .tag(false)
            }
            .pickerStyle(.segmented)
            
            Group {
                if viewModel.isShowingTag {
                    TagsView()
                        .transition(.blurReplace)
                } else {
                    HistoryView()
                        .transition(.blurReplace)
                }
            }
            .padding(.vertical, 20)
        }
        .animation(.easeInOut, value: viewModel.isShowingTag)
    }
    
    init(project: Binding<Project>) {
        self._project = project
        self.viewModel = ViewModel(image: project.image.wrappedValue)
    }
}

#Preview {
    @Previewable @State var project = Project(title: "Кейс №5", image: Image("Bird"))
    ProjectVeryDetailView(project: $project)
}
