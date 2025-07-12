//
//  TaskCardView.swift
//  iosApp
//
//  Created by Никита Арабчик on 12.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

class Tasks: Identifiable {
    var id = UUID()
    var title: String = ""
    var description: String = ""
    var timeType: Timing = .range
    var startDate: Date?
    var endDate: Date?
    var tags: [Tag] = [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
    
    init(title: String, description: String, timeType: Timing, startDate: Date? = nil, endDate: Date? = nil, tags: [Tag]) {
        self.title = title
        self.description = description
        self.timeType = timeType
        self.startDate = startDate
        self.endDate = endDate
        self.tags = tags
    }
}

struct TaskCardView: View {
    var task: Tasks
    
    var timeFormatter: DateFormatter {
        let formatter = DateFormatter()
        formatter.dateStyle = .none
        formatter.timeStyle = .short
        return formatter
    }
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 8)
                .fill(.background)
                .shadow(color: .primary.opacity(0.25), radius: 4, y: 1)
            
            HStack(alignment: .center) {
                VStack {
                    if let startDate = task.startDate {
                        Text(timeFormatter.string(from: startDate))
                    }
                    if let endDate = task.endDate {
                        Text("-")
                            .padding(.vertical, -20)
                        Text(timeFormatter.string(from: endDate))
                            .padding(.top, -10)
                    }
                }
                .font(.system(size: 20))
                .foregroundStyle(.accent)
                .padding(.trailing, 10)
                
                VStack(alignment: .leading, spacing: 5) {
                    Text(task.title)
                        .font(.system(size: 18))
                    
                    Text(task.description)
                        .font(.system(size: 12))
                        .multilineTextAlignment(.leading)
                        .foregroundStyle(.secondary)
                }
                .fontWeight(.regular)
                Spacer()
                
                VerticalTags(tags: task.tags)
            }
            .padding(.horizontal)
        }
        .padding(.horizontal, 10)
        .frame(width: 400, height: 70)
        .padding(.bottom, 10)
    }
}

struct VerticalTags: View {
    var tags: [Tag]
    let columns = [GridItem(.fixed(16)), GridItem(.fixed(16)), GridItem(.fixed(16))]

    var body: some View {
         ScrollView {
             LazyVGrid(columns: columns) {
                 ForEach(tags) { tag in
                     RoundedRectangle(cornerRadius: 2)
                         .fill(tag.color)
                         .frame(width: 18, height: 18)
                 }
             }
         }
         .frame(width: 60, height: 50)
    }
}

#Preview {
    TaskCardView(task: Tasks(title: "Поход в массажку", description: "Описание описание описание описание описание ", timeType: .deadline, startDate: Date(),endDate: Date(),   tags: [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple),Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple), Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple), Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple), Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple), Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]))
}
