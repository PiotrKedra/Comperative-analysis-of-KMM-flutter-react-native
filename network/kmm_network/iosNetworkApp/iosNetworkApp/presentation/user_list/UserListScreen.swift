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
        VStack {
            Text("\(viewModel.state.page)")
            Button(
                action: {
                    viewModel.updateState(page: Int(viewModel.state.page) + 1)
                },
                label: {
                    Text("Increment Page")
                }
            )
        
        }
    }
}
