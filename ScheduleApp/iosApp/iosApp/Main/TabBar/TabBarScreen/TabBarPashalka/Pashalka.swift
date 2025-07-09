//
//  SwiftUIView.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct Pashalka: View {
    var url = URL(string: "https://ibb.co/VFfFsSW")
    var body: some View {
        AsyncImage(url: url) { image in
                image
                    .resizable()
        } placeholder: {
            ProgressView()
        }
    }
    
}

#Preview {
    Pashalka()
}
