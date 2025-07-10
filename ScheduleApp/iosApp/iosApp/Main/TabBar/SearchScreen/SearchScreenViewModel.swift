//
//  SearchScreenViewModel.swift
//  iosApp
//
//  Created by Никита Арабчик on 09.07.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI


//MARK: CODE TO COMPLETE
extension SearchView {
    
    @Observable
    class ViewModel {
        private var tags = TagsMock.words
        var results = [String]()
        
        var inputText = "" {
            didSet {
                findVariants()
            }
        }
        var items: [String] = UserDefaults.standard.stringArray(forKey: "savedItems") ?? [] {
            didSet {
                UserDefaults.standard.set(items, forKey: "savedItems")
            }
        }
        
        func addItem() {
            let search = inputText.capitalized
            
            items.removeAll { $0 == search}
            
            if items.count == 5 {
                items.removeLast()
            }
            
            items.insert(search, at: 0)
        }
        
        func tagTap(item: String) {
            inputText = item.capitalized
            addItem()
        }
        
        private func findVariants() {
            if !inputText.isEmpty {
                results = tags.filter { $0.fuzzySearch(stringToSearch: inputText)}
            } else {
                results = []
            }
        }
        
    }
    
    final class TagsMock {
        static let words = [
            "яблоко",
            "банан",
            "вишня",
            "груша",
            "дыня",
            "ежевика",
            "жасмин",
            "зебра",
            "игла",
            "йогурт"
        ]
    }
}
