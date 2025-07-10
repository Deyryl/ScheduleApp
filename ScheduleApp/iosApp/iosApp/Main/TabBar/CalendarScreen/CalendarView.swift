//
//  CalendarView.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct CalendarView: View {
    var body: some View {
        VStack {
            Text("CalendarView")
            Button("Выход") {
                UserDefaults.standard.set(false, forKey: "UserLogged")
            }
        }
    }
}

#Preview {
    CalendarView()
}
