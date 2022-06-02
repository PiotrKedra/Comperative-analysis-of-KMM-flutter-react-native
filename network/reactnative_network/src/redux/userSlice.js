import { createSlice } from '@reduxjs/toolkit'

const userSlice = createSlice({
  name: 'users',
  initialState: [],
  reducers: {
    loadAllUsers(state, action) {
      return action.payload
    },
    addUser(state, action) {
      state.push(action.payload.user)
    },
    removeUser(state, action) {
      state.remove(action.payload.user)
    },
    updateUser(state, action) {
      const user = state.find(user => user.id === action.payload.user.id)
      user.email = action.payload.user.email
      user.firstName = action.payload.user.firstName
      user.lastName = action.payload.user.lastName
    }
  }
})

export const { loadAllUsers, addUser, removeUser, updateUser } = userSlice.actions
export default userSlice.reducer
