import SwiftUI

@main
struct iOSApp: App {
    @Environment(\.colorScheme) var systemColorScheme
    @AppStorage("theme") var currentTheme: String = "dark"
    @AppStorage("UserLogged") var userLogged: Bool = false
    @AppStorage("isFirstLaunch") var isFirstLaunch: Bool = true
    
    var body: some Scene {
        WindowGroup {
            Group {
                if !userLogged {
                    StartScreenView()
                } else {
                    ContentView()
                }
            }
            .onAppear {
                if isFirstLaunch {
                    currentTheme = systemColorScheme != .dark ? "dark" : "light"
                    isFirstLaunch = false
                }
            }
            .preferredColorScheme(currentTheme == "dark" ? .dark : .light)
        }
    }
}
