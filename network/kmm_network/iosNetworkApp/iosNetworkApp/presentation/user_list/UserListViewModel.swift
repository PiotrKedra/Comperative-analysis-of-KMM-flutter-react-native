import SwiftUI
import shared

class UserListViewModel: ObservableObject {
    
    let getUserList: GetUserList
    
    @Published var state: UserListState = UserListState(isLoading: false, page: 1, users: [User]())
    
    init(getUserList: GetUserList) {
        self.getUserList = getUserList
        print("SHITEN")
        loadUserList()
    }
    
    func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil
    ) {
        let currentState = (self.state.copy() as! UserListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            users: currentState.users
        )
    }
    
    func loadUserList() {
        let currentState = (self.state.copy() as! UserListState)
        do {
            try getUserList.execute(
                page: Int32(currentState.page)
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    print("in callback")
                    print(dataState)
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false
                        
                        self.updateState(isLoading: loading)
                        
                        if data != nil {
                            self.appendUserList(userList: data as! [User])
                        }
                        
                        if message != nil {
                            print("Error message: \((message ?? "NON ERROR") as String)")
                        }
                    }
                }
            )
        } catch {
            print("\(error)")
        }
    }
    
    func appendUserList(userList: [User]) {
        for user in userList {
            print("\(user.email)")
        }
    }
}
