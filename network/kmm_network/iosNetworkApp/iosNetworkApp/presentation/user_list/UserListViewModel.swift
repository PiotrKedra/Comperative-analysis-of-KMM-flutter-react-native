import SwiftUI
import shared

class UserListViewModel: ObservableObject {
    
    let getUserList: GetUserList
    
    @Published var state: UserListState = UserListState(isLoading: false, page: 1, users: [User]())

    @Published var lastEmailInTheList: String = ""
    
    init(getUserList: GetUserList) {
        self.getUserList = getUserList
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
        getUserList.execute(
            page: Int32(currentState.page)
        ).collectCommon(
            coroutineScope: nil,
            callback: { dataState in
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
    }
    
    func refresh() {
        getUserList.executeJustCache(
                page: 1
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false
                        
                        self.updateState(isLoading: loading)
                        
                        if data != nil {
                            self.state = self.state.doCopy(
                                isLoading: loading,
                                page: 1,
                                users: data as! [User]
                            )
                            self.lastEmailInTheList = (data as! [User]).last?.email ?? ""
                        }
                        
                        if message != nil {
                            print("Error message: \((message ?? "NON ERROR") as String)")
                        }
                    }
                }
            )
    }
    
    func appendUserList(userList: [User]) {
        let currentState = (self.state.copy() as! UserListState)
        let newUserList = currentState.users + userList
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            users: newUserList
        )
        self.lastEmailInTheList = newUserList.last?.email ?? ""
    }
    
    func shouldQueryNextUserPage(user: User) -> Bool {
        if ((user.email == self.lastEmailInTheList)
            && isCurrentPageTooBig()
            && !self.state.isLoading)
        {
            return true
        }
        return false
    }
    
    private func isCurrentPageTooBig() -> Bool {
        return 6 * self.state.page <= self.state.users.count
    }
    
    func nextPage() {
        let currentSatate = (self.state.copy() as! UserListState)
        self.updateState(page: Int(currentSatate.page) + 1)
        self.loadUserList()
    }
}
