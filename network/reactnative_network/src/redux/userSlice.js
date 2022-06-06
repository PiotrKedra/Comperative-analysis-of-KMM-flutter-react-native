import { createSlice } from '@reduxjs/toolkit'

const userSlice = createSlice({
  name: 'users',
  initialState: [],
  reducers: {
    saveUserList(state, action) {
      return action.payload
    },
  }
})

export const { saveUserList } = userSlice.actions
export default userSlice.reducer
