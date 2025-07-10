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
                ForEach($viewModel.projects) { $project in
                    ProjectCard(project: $project)
                        .listRowSeparator(.hidden)
                        .onTapGesture {
                            print(project.title.count)
                        }
                }
                
                Button("+ Добавить") {
                    viewModel.project = Project(title: "")
                }
                .font(.system(size: 25))
                .frame(maxWidth: .infinity)
                .listRowSeparator(.hidden)
                .foregroundStyle(.accent)
                .buttonStyle(.bordered)
                .fullScreenCover(item: $viewModel.project) { project in
                    ProjectEditView(project) { project in
                        viewModel.save(project)
                    }
                }
            }
            .navigationTitle("Мои расписания")
            .tint(.accent)
            .listStyle(.plain)
        }
    }
}

#Preview {
    MySchedulesView()
}
