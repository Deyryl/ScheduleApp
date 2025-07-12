//
//  HistoryView.swift
//  iosApp
//
//  Created by Никита Арабчик on 12.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct HistoryView: View {
    let tasksArray = [
        Tasks(
            title: "Поход в массажку",
            description: "Описание описание описание",
            timeType: .deadline,
            startDate: Date(),
            endDate: Calendar.current.date(byAdding: .day, value: 1, to: Date())!,
            tags: [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
        ),
        Tasks(
            title: "Поход в массажку",
            description: "Описание описание описание",
            timeType: .deadline,
            startDate: Calendar.current.date(byAdding: .day, value: 2, to: Date())!,
            endDate: Calendar.current.date(byAdding: .day, value: 3, to: Date())!,
            tags: [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
        ),
        Tasks(
            title: "Поход в массажку",
            description: "Описание описание описание",
            timeType: .deadline,
            startDate: Calendar.current.date(byAdding: .month, value: 1, to: Date())!,
            endDate: Calendar.current.date(byAdding: .weekOfYear, value: 2, to: Date())!,
            tags: [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
        ),
        Tasks(
            title: "Поход в массажку",
            description: "Описание описание описание",
            timeType: .deadline,
            startDate: Calendar.current.date(byAdding: .year, value: 1, to: Date())!,
            endDate: Calendar.current.date(byAdding: .month, value: 6, to: Date())!,
            tags: [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
        ),
        Tasks(
            title: "Поход в массажку",
            description: "Описание описание описание",
            timeType: .deadline,
            startDate: Calendar.current.date(byAdding: .day, value: -5, to: Date())!,
            endDate: Calendar.current.date(byAdding: .hour, value: -12, to: Date())!,
            tags: [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
        )
    ]
    
    var sortedTasks: [Tasks] {
        let filteredTask = tasksArray.filter { Date() > $0.endDate ?? $0.startDate! }
        
        return filteredTask.sorted {
            
            if let startDate1 = $0.startDate {
                if let startDate2 = $1.startDate {
                    return startDate1 > startDate2
                } else if let EndDate2 = $1.endDate  {
                    return startDate1 > EndDate2
                }
            } else if let endDate1 = $0.endDate {
                if let startDate2 = $1.startDate {
                    return endDate1 > startDate2
                } else if let EndDate2 = $1.endDate  {
                    return endDate1 > EndDate2
                }
            }
            
            
            return false
        }
    }
    
    var body: some View {
        ScrollView {
            //MARK: CODE TO COMPLETE
            ForEach(0..<sortedTasks.count) { index in
                if index == 0 {
                    DateView(date: sortedTasks[index].startDate ?? sortedTasks[index].endDate!)
                    TaskCardView(task: sortedTasks[index])
                } else if (sortedTasks[index].startDate ?? sortedTasks[index].endDate!) != (sortedTasks[index - 1].startDate ?? sortedTasks[index - 1].endDate!){
                    DateView(date: sortedTasks[index].startDate ?? sortedTasks[index].endDate!)
                    TaskCardView(task: sortedTasks[index])
                } else {
                    TaskCardView(task: sortedTasks[index])
                }
            }
        }
    }
}

#Preview {
    HistoryView()
}
