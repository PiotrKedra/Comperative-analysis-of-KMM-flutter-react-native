import SwiftUI
import shared

@main
struct iOSApp: App {
    
    
    
    private let networkModule = NetworkModule()
    private let cacheModule = CacheModule()
    @ObservedObject var viewModel: UserListViewModel
    
    init() {
        let userUseCaseModule = UserModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        self.viewModel = UserListViewModel(getUserList: userUseCaseModule.getUserList)
    }

	var body: some Scene {
		WindowGroup {
            UserListScreen(
                viewModel: self.viewModel,
                userUseCaseModule: UserModule(networkModule: self.networkModule, cacheModule: self.cacheModule)
            )
		}
	}
}
