//
//  TagInTaskView.swift
//  iosApp
//
//  Created by Никита Арабчик on 10.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct TagInTaskView: View {
    var tag: Tag
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Rectangle()
                    .fill(tag.color)
                    .frame(width: 20, height: 20)
                
                Text(tag.title)
                    .foregroundStyle(tag.color)
                    .frame(width: 100, alignment: .leading)
                
                Text(tag.description)
                    .foregroundStyle(.secondary)
                    .multilineTextAlignment(.leading)
                    .frame(maxWidth: .infinity, maxHeight: 40, alignment: .leading)
                
            }
            .font(.system(size: 16))
            
            
            Divider()
                .background(Color.accent)
        }
    }
}

#Preview {
    TagInTaskView(tag: Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple))
}
