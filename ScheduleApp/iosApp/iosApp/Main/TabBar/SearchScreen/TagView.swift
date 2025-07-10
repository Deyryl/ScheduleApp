//
//  TagView.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct TagView: View {
    var image: Image
    var title: String
    
    var body: some View {
        VStack {
            HStack(alignment: .center) {
                image
                    .resizable()
                    .scaledToFit()
                    .frame(maxWidth: 22, maxHeight: 22)
                    .padding(.horizontal, 6)
                    .fontWeight(.light)
                
                    Text(title)
                        .font(.system(size: 22))
                
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            
            RoundedRectangle(cornerRadius: 3)
                .fill(.accent.opacity(0.75))
                .frame(height: 1)
                .padding(.top, -5)
        }
        .padding(.horizontal, 20)
    }
    
    init(title: String, systemImage: String) {
        self.image = Image(systemName: systemImage)
        self.title = title
    }
}

#Preview {
    TagView(title: "Домашнее", systemImage: "tag")
}
