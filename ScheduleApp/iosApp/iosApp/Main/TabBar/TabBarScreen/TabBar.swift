import SwiftUI

struct TabBar: View {
    @StateObject private var viewModel = ViewModel()
    @State private var selection: String = "Поиск"

    var body: some View {
        NavigationStack(path: $viewModel.path) {
            Group {
                if #available(iOS 18.0, *) {
                    // — iOS 18+ Tab API —
                    TabView(selection: $selection) {
                        // Поиск
                        Tab {
                            SearchView()
                        } label: {
                            Label("Поиск", systemImage: "magnifyingglass")
                                .onTapGesture {
                                    // если уже на «Поиск», то просто пушим,
                                    // иначе меняем selection (и onChange добавит пуш)
                                    if selection == "Поиск" {
                                        viewModel.path.append("Поиск")
                                    } else {
                                        selection = "Поиск"
                                    }
                                }
                        }
                        .tag("Поиск")

                        // Расписание
                        Tab {
                            ScheduleView()
                        } label: {
                            Label("Расписание", systemImage: "note.text")
                                .onTapGesture {
                                    if selection == "Расписание" {
                                        viewModel.path.append("Расписание")
                                    } else {
                                        selection = "Расписание"
                                    }
                                }
                        }
                        .tag("Расписание")

                        // Календарь
                        Tab {
                            CalendarView()
                        } label: {
                            Label("Календарь", systemImage: "calendar")
                                .onTapGesture {
                                    if selection == "Календарь" {
                                        viewModel.path.append("Календарь")
                                    } else {
                                        selection = "Календарь"
                                    }
                                }
                        }
                        .tag("Календарь")

                        // Меню
                        Tab {
                            MenuView()
                        } label: {
                            Label("Меню", systemImage: "line.3.horizontal")
                                .onTapGesture {
                                    if selection == "Меню" {
                                        viewModel.path.append("Меню")
                                    } else {
                                        selection = "Меню"
                                    }
                                }
                        }
                        .tag("Меню")
                    }

                } else {
                    // — iOS <18 API —
                    TabView(selection: $selection) {
                        SearchView()
                            .tabItem {
                                Label("Поиск", systemImage: "magnifyingglass")
                                    .onTapGesture {
                                        if selection == "Поиск" {
                                            viewModel.path.append("Поиск")
                                        } else {
                                            selection = "Поиск"
                                        }
                                    }
                            }
                            .tag("Поиск")

                        ScheduleView()
                            .tabItem {
                                Label("Расписание", systemImage: "note.text")
                                    .onTapGesture {
                                        if selection == "Расписание" {
                                            viewModel.path.append("Расписание")
                                        } else {
                                            selection = "Расписание"
                                        }
                                    }
                            }
                            .tag("Расписание")

                        CalendarView()
                            .tabItem {
                                Label("Календарь", systemImage: "calendar")
                                    .onTapGesture {
                                        if selection == "Календарь" {
                                            viewModel.path.append("Календарь")
                                        } else {
                                            selection = "Календарь"
                                        }
                                    }
                            }
                            .tag("Календарь")

                        MenuView()
                            .tabItem {
                                Label("Меню", systemImage: "line.3.horizontal")
                                    .onTapGesture {
                                        if selection == "Меню" {
                                            viewModel.path.append("Меню")
                                        } else {
                                            selection = "Меню"
                                        }
                                    }
                            }
                            .tag("Меню")
                    }
                }
            }
            .tint(.accentColor)
            // срабатывает только при смене selection
            .onChange(of: selection) { newTab in
                viewModel.path.append(newTab)
            }
            // разбираем строки из path
            .navigationDestination(for: String.self) { route in
                switch route {
                case "Поиск":
                    SearchView()
                case "Расписание":
                    ScheduleView()
                case "Календарь":
                    CalendarView()
                case "Меню":
                    MenuView()
                default:
                    EmptyView()
                }
            }
        }
    }
}

// MARK: — ViewModel

final class ViewModel: ObservableObject {
    @Published var path = NavigationPath()
}

// MARK: — Preview

#Preview {
    TabBar()
}
