import SwiftUI
import shared

class UserDetailsViewModel: ObservableObject {
    
    private let deleteUser: DeleteUser
    let refreshCallback: () -> Void
    
    let userUseCaseModule: UserModule
    
    @Published var state: BasicState
    
    init(user: User, userUseCaseModule: UserModule, refreshCallback: @escaping () -> Void) {
        self.state = BasicState(isLoading: false, user: user)
        self.userUseCaseModule = userUseCaseModule
        self.deleteUser = userUseCaseModule.deleteUser
        self.refreshCallback = refreshCallback
    }
    
    func deleteUserById(id: Int32) {
        deleteUser.execute(id: id)
            .collectCommon(coroutineScope: nil, callback: { dataState in
                if dataState != nil {
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false
                    
                    self.updateState(isLoading: loading)
                    
                    if (message != nil) {
                        print("Message after success delete: \(message!)")
                        self.refreshCallback()
                    }
                }
            })
    }
    
    private func updateState(isLoading: Bool?) {
        let currentState = (self.state.copy() as! BasicState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            user: currentState.user
        )
    }
    
}
