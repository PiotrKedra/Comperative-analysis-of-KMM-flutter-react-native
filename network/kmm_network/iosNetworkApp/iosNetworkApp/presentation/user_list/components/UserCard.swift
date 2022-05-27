import SwiftUI
import shared

struct UserCard: View {
    
    private let user: User
    
    init(user: User) {
        self.user = user
    }
    
    var body: some View {
        HStack {
            if #available(iOS 15.0, *) {
                AsyncImage(url: URL(string: user.avatar))
                    .frame(width: 120, height: 120)
                    .scaledToFill()
                    .clipped()
            } else {
                Spacer().frame(width: 120, height: 120)
            }
            VStack(alignment: .leading, spacing: 5) {
                Text("\(user.firstName) \(user.lastName)")
                Text("\(user.email)")
                Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
            }.frame(
                minWidth: 0,
                maxWidth: .infinity
              )
            .background(Color.blue)
        }
    }
}
