//
//  ProjectUsersView.swift
//  iosApp
//
//  Created by Никита Арабчик on 12.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

class User: Identifiable {
    var id = Int.random(in: 1...100000)
    var image: Image
    var name: String
    
    init(id: Int = Int.random(in: 1...100000), image: Image, name: String) {
        self.id = id
        self.image = image
        self.name = name
    }
}

struct ProjectUsersView: View {
    @Binding var users: [User]
    var ownerid = 1
    
    var body: some View {
        ScrollView {
            ForEach(users) { user in
                HStack {
                    user.image
                        .resizable()
                        .scaledToFill()
                        .frame(width: 50, height: 50)
                    
                    Text(user.name)
                        .font(.system(size: 18))
                        .padding(.bottom, 20)
                    
                    Spacer()
                    
                    if ownerid == user.id {
                        Text("Владелец")
                            .font(.system(size: 14))
                            .padding(.bottom, 20)
                            .foregroundStyle(.accent)
                    }
                }
                .padding(.horizontal, 14)
                .frame(maxWidth: .infinity)
            }
        }
    }
}

#Preview {
    @Previewable @State var users = [
        User(id: 1, image: Image("Bird"), name: "Кристина романова"),
        User(id: 2, image: Image("Bird"), name: "Кристина романова"),
        User(id: 3, image: Image("Bird"), name: "Кристина романова"),
    ]
    ProjectUsersView(users: $users)
}
