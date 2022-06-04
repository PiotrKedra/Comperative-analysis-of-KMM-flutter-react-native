import AsyncStorage from '@react-native-async-storage/async-storage';
import Toast from 'react-native-toast-message';

const USER_LIST_STORAGE_KEY = '@user_list_key'

export const cache_initUserList = async (userList) => {
  try {
    await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(userList))
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
      await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(appendedList))
      return appendedList
    } else {
      await AsyncStorage.setItem(USER_LIST_STORAGE_KEY, JSON.stringify(userList))
      return userList
    }
  } catch (e) {
    showErrorToast(e)
  }
}

const showErrorToast = (error) => {
  Toast.show({
    type: 'error',
    text1: 'Error',
    text2: error
  });
}
