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
        Group {
            if #available(iOS 18.0, *) {
                TabView {
                    Tab("Поиск", systemImage: "magnifyingglass") {
                        SearchView()
                    }
                    
                    
                    Tab("Расписание", systemImage: "note.text") {
                        ScheduleView()
                    }
                    
                    Tab("Календарь", systemImage: "calendar") {
                        CalendarView()
                    }
                    
                    Tab("Меню", systemImage: "line.3.horizontal") {
                        MenuView()
                    }
                }
            } else {
                TabView {
                    SearchView()
                        .tabItem {
                            Label("Поиск", systemImage: "magnifyingglass")
                        }
                    
                    ScheduleView()
                        .tabItem {
                            Label("Расписание", systemImage: "note.text")
                        }
                    
                    CalendarView()
                        .tabItem {
                            Label("Календарь", systemImage: "calendar")
                        }
                    
                    MenuView()
                        .tabItem {
                            Label("Меню", systemImage: "line.3.horizontal")
                        }
                }
            }
        }
        .tint(.accent)
    }
}

#Preview {
    TabBar()
}
