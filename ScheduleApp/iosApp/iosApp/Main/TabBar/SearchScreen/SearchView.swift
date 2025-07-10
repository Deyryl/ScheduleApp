//
//  SearchView.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct SearchView: View {
    @State private var viewModel = ViewModel()
    
    var body: some View {
        NavigationStack {
            ScrollView{
                LazyVStack {
                    if viewModel.inputText.isEmpty {
                        VStack {
                            Text("Недавнее")
                                .font(.headline)
                                .frame(maxWidth: .infinity, alignment: .leading)
                                .padding(.vertical, 10)
                                .padding(.horizontal, 20)
                            
                            ForEach(viewModel.items, id: \.self) { item in
                                TagView(title: item, systemImage: "tag")
                                    .listRowSeparatorTint(.accent)
                                    .onTapGesture {
                                        viewModel.tagTap(item: item)
                                    }
                            }
                        }
                        .transition(.asymmetric(insertion: .opacity, removal: .opacity))
                    } else {
                        VStack {
                            Text("Результаты")
                                .font(.headline)
                                .frame(maxWidth: .infinity, alignment: .leading)
                                .padding(.vertical, 10)
                                .padding(.horizontal, 20)
                            
                            ForEach(viewModel.results, id: \.self) { item in
                                TagView(title: item, systemImage: "tag")
                                    .onTapGesture {
                                        viewModel.tagTap(item: item)
                                    }
                            }
                        }
                        .transition(.asymmetric(insertion: .opacity, removal: .opacity))
                    }
                }
                .animation(.easeInOut(duration: 0.4), value: viewModel.inputText.isEmpty)
            }
            .navigationTitle("Поиск")
            .searchable(text: $viewModel.inputText, prompt: "Расписания, теги, задачи")
            .onSubmit(of: .search) {
                viewModel.addItem()
            }
        }
        .tint(.accent)
        .padding(.horizontal, 10)
    }
}

#Preview {
    SearchView()
}
