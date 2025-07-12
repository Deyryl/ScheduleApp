//
//  ScheduleView.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MySchedulesView: View {
    @State private var viewModel = ViewModel()
    
    var body: some View {
        NavigationStack {
            List {
                ForEach($viewModel.sortedProjects) { $project in
                    NavigationLink {
                        ProjectDetailView(project: $project)
                            .navigationBarBackButtonHidden()
                    } label: {
                        ProjectCard(project: $project) { project in
                            viewModel.save(project: project)
                        }
                    }
                    
                    .listRowSeparator(.hidden)
                    .transition(.slide)
                }
            }
            .navigationTitle("Мои проекты")
            .listStyle(.plain)
            .animation(.easeInOut, value: viewModel.sortedProjects)
            .overlay(alignment: .bottom) {
                Button("+ Добавить") {
                    viewModel.project = Project(title: "")
                }
                .font(.system(size: 25))
                .buttonStyle(.bordered)
                .padding(.bottom, 50)
                .fullScreenCover(item: $viewModel.project) { project in
                    ProjectEditView(project: project) { project in
                        viewModel.save(project: project)
                    }
                }
            }
            .tint(.accent)
        }
    }
}

#Preview {
    MySchedulesView()
}
