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
            ZStack {
                VStack {
                    Text("User list")
                        .font(.largeTitle)
                        .padding()
                    
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
                
                NavigationLink(destination:
                        UserModificationScreen(
                            viewModel: UserModificationViewModel(
                                user: nil,
                                createUser: userUseCaseModule.createUser,
                                updateUser: userUseCaseModule.updateUser,
                                callback: viewModel.refresh
                        )))
                {
                    Text("ADD")
                        .foregroundColor(.white)
                        .frame(width: 80, height: 80)
                        .background(Color.red)
                        .cornerRadius(2)
                        .padding()
                }
                .position(x: 330, y: 730)
            }
        }
    }
}
