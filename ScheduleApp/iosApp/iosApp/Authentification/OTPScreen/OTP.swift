//
//  File.swift
//  iosApp
//
//  Created by Никита Арабчик on 07.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

enum TypingState {
    case typing
    case valid
    case invalid
}

struct VerificationField: View {
    var length: Int
    @Binding var value: String
    @State private var state: TypingState = .typing
    @State private var invalidTrigger = false
    var onChange: (String) async -> TypingState
    
    @FocusState private var isFocused: Bool
    
    var body: some View {
        HStack(spacing: 10) {
            ForEach(0..<length, id: \.self) { index in
                CharacterView(index: index)
            }
        }
        .compositingGroup()
        .phaseAnimator([0, 10, -10, 10, -5, 5, 0], trigger: invalidTrigger) { content, offset in
            content.offset(x: offset)
        } animation: { _ in
            .linear(duration: 0.06)
        }
        .background {
            TextField("", text: $value)
                .focused($isFocused)
                .keyboardType(.numberPad)
                .textContentType(.oneTimeCode)
                .mask(alignment: .leading) {
                    Rectangle()
                        .frame(width: 1, height: 1)
                        .opacity(0.01)
                }
                .allowsHitTesting(false)
        }
        .contentShape(.rect)
        .onTapGesture {
            isFocused = true
        }
        .onChange(of: value) { oldValue, newValue in
            value = String(newValue.prefix(length))
            Task {
                state = await onChange(value)
                if state == .invalid {
                    invalidTrigger.toggle()
                }
            }
        }
        .toolbar {
            ToolbarItem(placement: .keyboard) {
                Button("Готово") {
                    isFocused = false
                }
                .tint(.primary)
                .frame(maxWidth: .infinity, alignment: .trailing)
            }
        }
        .onAppear {
            isFocused = true
        }
    }
    
    @ViewBuilder
    func CharacterView(index: Int) -> some View {
        Group {
            Rectangle()
                .fill(borderColor(index))
                .frame(height: 3)
                .frame(minHeight: 40, alignment: .bottom)
        }
        .frame(width: 40, height: 50)
        .overlay {
            let stringValue = string(index)
            
            if stringValue != "" {
                Text(stringValue)
                    .font(.title2)
                    .fontWeight(.semibold)
                    .transition(.blurReplace)
            }
        }
    }
    
    func string(_ index: Int) -> String {
        if value.count > index {
            let startIndex = value.startIndex
            let stringIndex = value.index(startIndex, offsetBy: index)
            
            return String(value[stringIndex])
        }
        
        return "" 
    }
    
    func borderColor(_ index: Int) -> Color {
        switch state {
        case .invalid:
            return .red
        case .typing:
            return value.count == index && isFocused ? .primary : .secondary
        case .valid:
            return .green
        }
    }
}

#Preview {
    CodeAuthentificationView()
}
