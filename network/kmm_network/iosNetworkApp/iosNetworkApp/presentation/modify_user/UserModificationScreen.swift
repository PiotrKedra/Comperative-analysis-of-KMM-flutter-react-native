//
//  UserModificationScreen.swift
//  iosNetworkApp
//
//  Created by Marcelina Banaś on 27/05/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct UserModificationScreen: View {
    
    private let user: User?
    
    init(user: User? = nil) {
        self.user = user
    }
    
    var body: some View {
        if (user == nil) {
            Text("User Creation Screen")
        } else {
            Text("User Update Screen")
        }
    }
}
