import SwiftUI
import shared

struct UserDetailsScreen: View {
    
    @ObservedObject var viewModel: UserDetailsViewModel
    
    @Environment(\.presentationMode) var presentation
    
    init(viewModel: UserDetailsViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("\(self.viewModel.state.user!.firstName) \(self.viewModel.state.user!.lastName)")
                .font(.largeTitle)
            
            if #available(iOS 15.0, *) {
                AsyncImage(
                    url: URL(string: self.viewModel.state.user!.avatar),
                    content: { image in
                        image.resizable()
                            .frame(width: 250, height: 250)
                            .aspectRatio(contentMode: .fit)
                            .cornerRadius(30)
                    },
                    placeholder: {
                        ProgressView()
                    }
                )
            } else {
                Spacer().frame(width: 250, height: 250)
            }
            Text("\(self.viewModel.state.user!.email)")
                .font(.title3)
                .foregroundColor(.gray)
            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .font(.title3)
            
            
            HStack {
                Button("Delete user") {
                    self.viewModel.deleteUserById(id: self.viewModel.state.user!.id)
                    self.presentation.wrappedValue.dismiss()
                }
                .foregroundColor(.black)
                .frame(width: 130, height: 50)
                .background(Color.white)
                .cornerRadius(2)
                .font(.title2)
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
                        .frame(width: 130, height: 50)
                        .background(Color.red)
                        .cornerRadius(2)
                        .font(.title2)
                }
            }
        }
        .frame(
            minWidth: 0,
            maxWidth: .infinity,
            minHeight: 0,
            maxHeight: .infinity,
            alignment: .topLeading
        )
        .padding(20)
    }
}

