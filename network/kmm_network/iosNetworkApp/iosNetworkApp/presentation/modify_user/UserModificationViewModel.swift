import SwiftUI
import shared

class UserModificationViewModel: ObservableObject {
    
    let createUser: CreateUser
    let updateUser: UpdateUser
    private let refreshCallback: () -> Void
    
    @Published var state: BasicState
    
    init(user: User?, createUser: CreateUser, updateUser: UpdateUser, callback: @escaping () -> Void) {
        self.state = BasicState(isLoading: false, user: user)
        self.createUser = createUser
        self.updateUser = updateUser
        self.refreshCallback = callback
    }
    
    private func updateState(isLoading: Bool?) {
        let currentState = (self.state.copy() as! BasicState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            user: currentState.user
        )
    }
    
    func createNewUser(user: User) {
        createUser.execute(user: user)
            .collectCommon(coroutineScope: nil, callback: { dataState in
                self.handleModification(dataState: dataState, dataMessage: "User created")
            })
    }
    
    func updateUserEntity(user: User) {
        updateUser.execute(user: user)
            .collectCommon(coroutineScope: nil, callback: { dataState in
                self.handleModification(dataState: dataState, dataMessage: "User updated")
            })
    }
    
    private func handleModification(dataState: DataState<User>?, dataMessage: String) {
        if dataState != nil {
            let data = dataState?.data
            let message = dataState?.message
            let loading = dataState?.isLoading ?? false
            
            self.updateState(isLoading: loading)
            
            if (data != nil) {
                print("\(dataMessage)")
                self.refreshCallback()
            }
            
            if (message != nil) {
                print("Message after modification: \(message!)")
            }
        }
    }
}
