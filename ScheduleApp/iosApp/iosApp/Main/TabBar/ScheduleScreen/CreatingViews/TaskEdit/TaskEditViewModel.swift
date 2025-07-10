//
//  TaskEditViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//
//
//Binding(
// get: { viewModel.startDate ?? Date() },
// set: { viewModel.startDate = $0}
//),
import SwiftUI

struct Tag: Identifiable{
    var id: UUID = UUID()
    var title: String
    var description: String
    var color: Color
}

extension Binding where Value == Date? {
    func withDefault(_ defaultValue: Date = Date()) -> Binding<Date> {
        Binding<Date>(
            get: { self.wrappedValue ?? defaultValue },
            set: { self.wrappedValue = $0 }
        )
    }
}


extension TaskEditView {
    
    @Observable
    class ViewModel {
        var title: String = ""
        var description: String = ""
        var timeType: TimeType = .range
        var startDate: Date?
        var endDate: Date?
        var tag: Tag?
        //MARK: CODE TO COMPLETE
        var tags: [Tag] = [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
        
//        init(title: String, description: String, timeType: TimeType, startDate: Date? = nil, endDate: Date? = nil, tags: [Tag]) {
//            self.title = title
//            self.description = description
//            self.timeType = timeType
//            self.startDate = startDate
//            self.endDate = endDate
//            self.tags = tags
//        }
        
        func saveTask() {
            
        }
        
        func saveTag(savingTag: Tag) {
            if let index = tags.firstIndex(where: {savingTag.id == $0.id}) {
                tags[index] = savingTag
            } else {
                tags.append(savingTag)
            }
        }
    }
    
    enum TimeType: String, CaseIterable  {
        case deadline = "Дедлайн"
        case endsAt = "Выполнить после..."
        case range = "Диапазон"
    }
}
