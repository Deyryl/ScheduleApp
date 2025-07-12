//
//  ProjectDetailViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 12.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI


extension ProjectDetailView {
    @Observable
    class ViewModel {
        var image: Image
        var isSelected = false
        var isAdding = false
        
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
                startDate: Calendar.current.date(byAdding: .day, value: 5, to: Date())!,
                endDate: Calendar.current.date(byAdding: .hour, value: 12, to: Date())!,
                tags: [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
            )
        ]
        
        var sortedTasks: [Tasks] {
            tasksArray.sorted {
                if let startDate1 = $0.startDate {
                    if let startDate2 = $1.startDate {
                        return startDate1 < startDate2
                    } else if let EndDate2 = $1.endDate  {
                        return startDate1 < EndDate2
                    }
                } else if let endDate1 = $0.endDate {
                    if let startDate2 = $1.startDate {
                        return endDate1 < startDate2
                    } else if let EndDate2 = $1.endDate  {
                        return endDate1 < EndDate2
                    }
                }
                return false
            }
        }
        
        init(image: Image?) {
            if let image = image {
                self.image = image
            } else {
                self.image = Image(systemName: "photo")
            }
        }
    }
}
