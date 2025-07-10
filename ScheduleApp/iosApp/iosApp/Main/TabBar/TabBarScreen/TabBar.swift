//
//  TabBar.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI


struct TabBar: View {
    var body: some View {
        TabView {
            SearchView()
                .tabItem {
                    Label("Поиск", systemImage: "magnifyingglass")
                }
                .toolbarBackground(.visible, for: .tabBar)
            
            ScheduleView()
                .tabItem {
                    Label("Расписание", systemImage: "note.text")
                }
                .toolbarBackground(.visible, for: .tabBar)
            
            CalendarView()
                .tabItem {
                    Label("Календарь", systemImage: "calendar")
                }
                .toolbarBackground(.visible, for: .tabBar)
            
            MenuView()
                .tabItem {
                    Label("Меню", systemImage: "line.3.horizontal")
                }
                .toolbarBackground(.visible, for: .tabBar)
        }
        .toolbarBackground(.secondary.opacity(0.04), for: .tabBar)
        .tint(.accent)
    }
}

#Preview {
    TabBar()
}
