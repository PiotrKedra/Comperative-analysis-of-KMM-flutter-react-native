import SwiftUI
import shared

struct UserDetailsScreen: View {
    
    private let user: User
    
    init(user: User) {
        self.user = user
    }
    
    var body: some View {
        Text("User Details Screen")
        NavigationLink(destination: UserModificationScreen(user: self.user)){
            Text("TESCIOR")
                .foregroundColor(.white)
                .frame(width: 200, height: 40)
                .background(Color.green)
                .cornerRadius(15)
                .padding()
        }
    }
}

