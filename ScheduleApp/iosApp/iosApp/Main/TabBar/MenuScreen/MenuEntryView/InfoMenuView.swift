//
//  InfoMenuView.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct InfoMenuView: View {
    var body: some View {
        VStack {
            Spacer()
                .frame(maxHeight: 120)
            
            AppTitleView()
            
            Text("""
            Свяжитесь с нами
            o736b02@voenmeh.ru
            БГТУ Военмех им. Д. Ф. Устинова
            """)
            .multilineTextAlignment(.center)
            .font(.system(size: 20))
            .tint(.accent)
            .padding(.vertical, 40)
            .padding(.bottom, 40)
            Text("""
            УСЛОВИЯ ОБСЛУЖИВАНИЯ
            ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ
            ОТКРЫТОЕ ПРОГРАММНОЕ ОБЕСПЧЕНИЕ
            """)
            .multilineTextAlignment(.center)
            .font(.system(size: 20))
            .foregroundStyle(.accent)
            .tint(.accent)
            
            Spacer()
            
            Text("2025 все права защищены бля буду")
                .font(.system(size: 13))
        }
        .fontWeight(.medium)
    }
}

#Preview {
    NavigationStack {
        InfoMenuView()
    }
}
