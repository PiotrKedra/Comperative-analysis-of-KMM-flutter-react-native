import SwiftUI
import shared

var BASIC_AVATAR_URL = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687"


struct UserModificationScreen: View {
        
    @ObservedObject var viewModel: UserModificationViewModel
    
    @State private var firstName: String = ""
    @State private var lastName: String = ""
    @State private var email: String = ""
    
    @Environment(\.presentationMode) var presentation
    
    init(viewModel: UserModificationViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            if (self.viewModel.state.user == nil) {
                Text("Create new user")
            } else {
                Text("Update user")
                Text("\(self.viewModel.state.user?.email ?? "xd")")
            }
            TextField("First name", text: $firstName)
            TextField("Last name", text: $lastName)
            TextField("Email", text: $email)
            
            Button("Done") {
                if (self.viewModel.state.user == nil) {
                    self.createNewUser()
                } else {
                    self.updateUser()
                }
            }
        }
        .onAppear {
            let user = self.viewModel.state.user
            if (user != nil) {
                self.firstName = user!.firstName
                self.lastName = user!.lastName
                self.email = user!.email
            }
        }
    }
    
    private func createNewUser() {
        let newUser = User(
            id: Int32(Int.random(in: 1..<9999)),
            email: self.email,
            firstName: self.firstName,
            lastName: self.lastName,
            avatar: BASIC_AVATAR_URL
        )
        self.viewModel.createNewUser(user: newUser)
        self.presentation.wrappedValue.dismiss()
    }
    
    private func updateUser() {
        let user = User(
            id: self.viewModel.state.user!.id,
            email: self.email,
            firstName: self.firstName,
            lastName: self.lastName,
            avatar: self.viewModel.state.user!.avatar
        )
        self.viewModel.updateUserEntity(user: user)
        self.presentation.wrappedValue.dismiss()
        self.presentation.wrappedValue.dismiss()
    }
}
