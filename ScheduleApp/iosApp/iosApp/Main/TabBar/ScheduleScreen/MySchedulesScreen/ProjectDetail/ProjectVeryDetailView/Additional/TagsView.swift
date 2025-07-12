//
//  TagsView.swift
//  iosApp
//
//  Created by Никита Арабчик on 12.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct TagsView: View {
    var tags: [Tag] = [Tag(title: "Penis", description: "afdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfdaafdafadfda", color: .purple)]
    
    var body: some View {
        ScrollView {
            //MARK: CODE TO COMPLETE
            ForEach(tags) { tag in
                TagInTaskView(tag: tag)
                    .padding(.horizontal, 20)
            }
        }
    }
}

#Preview {
    TagsView()
}
