import AsyncStorage from '@react-native-async-storage/async-storage';
import Toast from 'react-native-toast-message';

const USER_LIST_STORAGE_KEY = '@user_list_key'

export const cache_initUserList = async (userList) => {
  try {
    await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(sortUserList(userList)))
    return userList
  } catch (e) {
    showErrorToast(e)
  }
}

export const cache_getUserList = async () => {
  try {
    const value = await AsyncStorage.getItem(USER_LIST_STORAGE_KEY)
    if (value != null) {
      return JSON.parse(value)
    } else {
      showErrorToast("User list not available in cache")
      return []
    }
  } catch (e) {
    showErrorToast(e)
  }
}

export const cache_appendUserList = async (userList) => {
  try {
    const value = await AsyncStorage.getItem(USER_LIST_STORAGE_KEY)
    if (value != null) {
      const currentUsers = JSON.parse(value)
      const appendedList = currentUsers.concat(userList)
      await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(sortUserList(appendedList)))
      return appendedList
    } else {
      await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(sortUserList(userList)))
      return userList
    }
  } catch (e) {
    showErrorToast(e)
  }
}

export const cache_addNewUser = async (user) => {
  try {
    const value = await AsyncStorage.getItem(USER_LIST_STORAGE_KEY)
    if (value != null) {
      const userList = JSON.parse(value)
      userList.push(user)
      await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(sortUserList(userList)))
      return userList
    } else {
      await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify([user]))
      return [user]
    }
  } catch (e) {
    showErrorToast(e)
  }
}

export const cache_updateUser = async (user) => {
  try {
    const value = await AsyncStorage.getItem(USER_LIST_STORAGE_KEY)
    if (value != null) {
      const currentUsers = JSON.parse(value)
      const userList = currentUsers.filter(u => u.id !== user.id)
      userList.push(user)
      await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(sortUserList(userList)))
      return userList
    } else {
      showErrorToast("Some error while updating the user")
      return []
    }
  } catch (e) {
    showErrorToast(e)
  }
}

export const cache_deleteUser = async (id) => {
  try {
    const value = await AsyncStorage.getItem(USER_LIST_STORAGE_KEY)
    if (value != null) {
      const currentUsers = JSON.parse(value)
      const userList = currentUsers.filter(u => u.id !== id)
      await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(sortUserList(userList)))
      return userList
    } else {
      showErrorToast("Some error while deleting the user")
      return []
    }
  } catch (e) {
    showErrorToast(e)
  }
}

const sortUserList = (userList) => {
  return userList.sort((a, b) => (a.id > b.id) ? 1 : -1)
}

const showErrorToast = (error) => {
  Toast.show({
    type: 'error',
    text1: 'Error',
    text2: error
  });
}
