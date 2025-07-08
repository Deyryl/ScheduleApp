import SwiftUI

@main
struct iOSApp: App {
    @AppStorage("UserLogged") var userLogged: Bool = false
    
	var body: some Scene {
		WindowGroup {
            if !userLogged {
                StartScreenView()
            } else {
               ContentView()
            }
		}
	}
}
