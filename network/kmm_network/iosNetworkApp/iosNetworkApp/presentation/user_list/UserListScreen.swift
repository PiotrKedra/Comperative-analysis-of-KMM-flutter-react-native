import SwiftUI
import shared

struct UserListScreen: View {
    
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let userUseCaseModule: UserModule
    
    @ObservedObject var viewModel: UserListViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.userUseCaseModule = UserModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        self.viewModel = UserListViewModel(getUserList: self.userUseCaseModule.getUserList)
    }
    
    var body: some View {
        NavigationView {
            VStack {
                NavigationLink(destination: UserModificationScreen()){
                    Text("TESCIOR")
                        .foregroundColor(.white)
                        .frame(width: 200, height: 40)
                        .background(Color.green)
                        .cornerRadius(15)
                        .padding()
                }
                List {
                    ForEach(viewModel.state.users, id: \.self.id) { user in

                        NavigationLink(destination: UserDetailsScreen(user: user)){
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
