import SwiftUI
import shared

struct UserListScreen: View {
    
    private let userUseCaseModule: UserModule
    
    @ObservedObject var viewModel: UserListViewModel
    
    init(viewModel: UserListViewModel, userUseCaseModule: UserModule) {
        self.userUseCaseModule = userUseCaseModule
        self.viewModel = viewModel
    }
    
    var body: some View {
        NavigationView {
            VStack {
                NavigationLink(destination:
                        UserModificationScreen(
                            viewModel: UserModificationViewModel(
                                user: nil,
                                createUser: userUseCaseModule.createUser,
                                updateUser: userUseCaseModule.updateUser,
                                callback: viewModel.refresh
                        ))){
                    Text("TESCIOR")
                        .foregroundColor(.white)
                        .frame(width: 200, height: 40)
                        .background(Color.green)
                        .cornerRadius(15)
                        .padding()
                }
                List {
                    ForEach(viewModel.state.users, id: \.self.id) { user in
                        let userDetailViewModel = UserDetailsViewModel(
                            user: user,
                            userUseCaseModule: self.userUseCaseModule,
                            refreshCallback: self.viewModel.refresh
                        )
                        NavigationLink(destination: UserDetailsScreen(viewModel: userDetailViewModel)
                        ) {
                            UserCard(user: user)
                                .onAppear(perform: {
                                    if (viewModel.shouldQueryNextUserPage(user: user)) {
                                        viewModel.nextPage()
                                    }
                                })
                        }
                        .frame(
                            minWidth: 0,
                            maxWidth: .infinity
                          )
                        .background(Color.red)
                        .listRowInsets(EdgeInsets())
                        .padding(.top, 10)
                        .buttonStyle(PlainButtonStyle())
                    }
                }
            }
            .navigationBarHidden(true)
            .frame(
              minWidth: 0,
              maxWidth: .infinity,
              minHeight: 0,
              maxHeight: .infinity,
              alignment: .topLeading
            )
            .background(Color.green)
            
        }
        
    }
}
