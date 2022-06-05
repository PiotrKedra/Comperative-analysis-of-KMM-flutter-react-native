import { api_createUser, api_deleteUser, api_getUserList, api_updateUser } from './userApi';
import {
  cache_addNewUser,
  cache_appendUserList, cache_deleteUser,
  cache_getUserList,
  cache_initUserList,
  cache_updateUser
} from './userCache';
import Toast from 'react-native-toast-message';

export const PAGINATION_PAGE_SIZE = 6

export const loadUserList = async () => {
  const userList = await api_getUserList(1)
  if (userList.data == null)
    return await cache_getUserList()
  return await cache_initUserList(userList.data)
}

export const loadUserListFromCache = async () => {
  return await cache_getUserList()
}

export const loadNextPageUserList = async (page) => {
  const userList = await api_getUserList(page)
  if (userList.data == null) {
    showErrorToast('Couldn\'t load next page')
    return await cache_getUserList()
  }
  return await cache_appendUserList(userList.data)
}

export const createUser = async (user) => {
  const createdUser = await api_createUser(user)
  if (createdUser.data == null) {
    showErrorToast('Couldn\'t create new user')
    return await cache_getUserList()
  }
  return await cache_addNewUser(createdUser.data)
}

export const updateUser = async (user) => {
  const updatedUser = await api_updateUser(user)
  if (updatedUser.data == null) {
    showErrorToast('There was problem with updating the user')
  }
  return await cache_updateUser(user)
}

export const deleteUser = async (id) => {
  const result = await api_deleteUser(id)
  if (result.data == null) {
    showErrorToast('There was problem with deleting the user')
  }
  return await cache_deleteUser(id)
}

const showErrorToast = (error) => {
  Toast.show({
    type: 'error',
    text1: 'Error',
    text2: error
  });
}
