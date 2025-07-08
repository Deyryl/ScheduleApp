//
//  MenuItemView.swift
//  iosApp
//
//  Created by Никита Арабчик on 08.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MenuItemView: View {
    var image: Image
    var title: String
    
    var body: some View {
        VStack {
            ZStack {
                RoundedRectangle(cornerRadius: 10)
                    .fill(.background)
                    .shadow(color: .primary.opacity(0.25) ,radius: 4, y: 1)
                    .frame(maxWidth: .infinity, maxHeight: 44)
                HStack {
                    image
                        .resizable()
                        .scaledToFit()
                        .frame(width: 26, height: 26)
                        .foregroundStyle(.accent)
                        .padding(.horizontal, 6)
                    Text(title)
                        .font(.system(size: 22))
                    Spacer()
                    
                    Image(systemName: "chevron.right")
                        .padding(.trailing, 10)
                        .foregroundStyle(.secondary)
                }
            }
        }
        .padding(.horizontal, 20)
    }
    
    init(_ title: String, image: String) {
        self.image = Image(image)
        self.title = title
    }
    
    init(_ title: String, systemImage: String) {
        self.image = Image(systemName: systemImage)
        self.title = title
    }
}

#Preview {
    MenuItemView("Settings", systemImage: "gear")
}
