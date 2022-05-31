import SwiftUI
import shared

struct UserDetailsScreen: View {
    
    @ObservedObject var viewModel: UserDetailsViewModel
    
    @Environment(\.presentationMode) var presentation
    
    init(viewModel: UserDetailsViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        Text("User Details Screen")
        Text("\(self.viewModel.state.user!.email)")
        NavigationLink(destination:
                UserModificationScreen(
                    viewModel: UserModificationViewModel(
                        user: self.viewModel.state.user,
                        createUser: self.viewModel.userUseCaseModule.createUser,
                        updateUser: self.viewModel.userUseCaseModule.updateUser,
                        callback: self.viewModel.refreshCallback
                ))){
            Text("Update user")
                .foregroundColor(.white)
                .frame(width: 200, height: 40)
                .background(Color.red)
                .cornerRadius(15)
                .padding()
        }
        Button("Delete user") {
            self.viewModel.deleteUserById(id: self.viewModel.state.user!.id)
            self.presentation.wrappedValue.dismiss()
        }
    }
}

