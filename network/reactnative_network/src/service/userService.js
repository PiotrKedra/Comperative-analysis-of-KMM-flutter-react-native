import { api_getUserList } from './userApi';
import { cache_appendUserList, cache_getUserList, cache_initUserList } from './userCache';
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
    showErrorToast()
    return await cache_getUserList(page)
  }
  return await cache_appendUserList(userList.data)
}

export const createUser = async (user) => {

}

export const updateUser = async (user) => {

}

export const deleteUser = async (user) => {

}

const showErrorToast = () => {
  Toast.show({
    type: 'error',
    text1: 'Error',
    text2: 'Couldn\'t load next page'
  });
}
