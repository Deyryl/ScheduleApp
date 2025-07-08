//
//  AppTitleView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AppTitleView: View {
    var body: some View {
        VStack {
            Text("Schedule")
                .font(.system(size: 65))
                .fontWeight(.medium)
            
            + Text("App")
                .font(.system(size: 65))
                .fontWeight(.medium)
                .foregroundStyle(.accent)
        }
        .accessibilityHidden(true)
    }
}

#Preview {
    AppTitleView()
}
