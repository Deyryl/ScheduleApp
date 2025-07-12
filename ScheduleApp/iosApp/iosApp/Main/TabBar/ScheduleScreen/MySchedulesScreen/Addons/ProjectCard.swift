//
//  ScheduleCard.swift
//  iosApp
//
//  Created by Никита Арабчик on 10.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct ProjectCard: View {
    @Binding var project: Project
    @State private var changingProject: Project?
    var saveOnChange: (Project) -> Void
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 10)
                .fill(.background)
                .shadow(color: .secondary, radius: 4, y: 1)
            
            HStack(alignment: .top) {
                if let image = project.image {
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(width: 90, height: 90)
                        .clipShape(.rect(cornerRadius: 10))
                        .padding(.horizontal, 8)
                } else {
                    VStack {
                        Image(systemName: "photo")
                            .font(.system(size: 50))
                    }
                    .foregroundStyle(.secondary)
                    .frame(width: 90, height: 90)
                    .padding(.horizontal, 8)
                }
                
                VStack {
                    HStack(alignment: .center) {
                        HStack {
                            Text(project.title)
                                .font(.system(size: 18))
                                .frame(maxHeight: 20)
                            if project.isPinned {
                                Image(systemName: project.isPinned ? "pin" : "")
                                    .font(.system(size: 10))
                                    .contentTransition(.symbolEffect(.replace))
                            }
                        }
                        .padding(.top, 10)
                        
                        Spacer()
                        
                        Menu("", systemImage: "ellipsis") {
                            Button(project.isPinned ? "Открепить" : "Закрепить", systemImage: project.isPinned ? "pin" : "pin.slash") {
                                    project.isPinned.toggle()
                            }
                            
                            Button("\(project.isSounded ? "Выкл." : "Вкл." ) уведомления", systemImage: project.isSounded ? "speaker.wave.3" : "speaker.slash") {
                                project.isSounded.toggle()
                            }
                            
                            Button("Изменить", systemImage: "square.and.pencil") {
                                changingProject = project
                            }
                        }
                        .menuStyle(.borderlessButton)
                    }
                }
            }
            .animation(.default, value: project.isPinned)
            .frame(maxWidth: 360, maxHeight: 110)
            .fullScreenCover(item: $changingProject) { project in
                ProjectEditView(project: project, save: saveOnChange)
            }
        }
        .padding(.bottom, 10)
    }
}
