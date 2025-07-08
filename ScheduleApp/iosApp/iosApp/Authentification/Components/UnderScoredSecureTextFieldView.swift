//
//  UnderScoredSecureTextFieldView.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct UnderScoredSecureTextFieldView: View {
    var name: String
    @Binding var text: String
    
    var body: some View {
        VStack {
            SecureField(name, text: $text)
                .font(.system(size: 24))
                .padding(.horizontal, 10)
            Divider()
                .frame(height: 2)
                .background(Color.accent)
                .clipShape(.capsule)
                .accessibilityHidden(true)
        }
    }
}

#Preview {
    @Previewable @State var email: String = ""
    UnderScoredSecureTextFieldView(name: "abob", text: $email)
}
