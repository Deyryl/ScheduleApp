//
//  RotationScaleModifier.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct RotationWithScaleModifier: ViewModifier {
    let amount: Double
    let scale: Double
    
    func body(content: Content) -> some View {
        content
            .rotation3DEffect(.degrees(amount), axis: (x: 1, y: 0, z: 0))
            .scaleEffect(scale)
    }
}

extension AnyTransition {
    static var scaleRotating: AnyTransition {
        .modifier(
            active: RotationWithScaleModifier(amount: 270, scale: 0),
            identity: RotationWithScaleModifier(amount: 0, scale: 1)
        )
    }
}
