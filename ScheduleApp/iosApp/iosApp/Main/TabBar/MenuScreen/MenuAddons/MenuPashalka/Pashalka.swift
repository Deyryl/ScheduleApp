//
//  SwiftUIView.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct Pashalka: View {
    var url = URL(string: "https://711515.selcdn.ru/recycleCDN/main/9/9ead29f7ebb448d64cd47e567738b77a_thumbnail.jpg")
    var body: some View {
        VStack {
            AsyncImage(url: url) { image in
                image
                    .resizable()
                    .scaledToFill()
                    .background(ignoresSafeAreaEdges: .all)
            } placeholder: {
                ProgressView()
            }
        }
    }
    
}

#Preview {
    Pashalka()
}
