import React from 'react';
import { Button, Text, View } from 'react-native';
import { useDispatch, useSelector } from 'react-redux';
import { loadAllUsers } from '../../redux/userSlice';
import { createUser, deleteUser, loadNextPageUserList, loadUserList, updateUser } from '../../service/userService';


const UserListView = () => {
  const userList = useSelector((state) => state.users)
  const dispatch = useDispatch()

  const [page, setPage] = React.useState(1)

  console.log('DUPA')

  React.useEffect(() => {
    loadUsers()
  }, [])

  const loadUsers = async () => {
    const s = await loadUserList()
    console.log(s)
  }

  const loadNextPage = async () => {
    const a = await loadNextPageUserList(page + 1)
    setPage(page + 1)
    console.log(a)
  }

  const addUser = async () => {
    const s = await deleteUser(1)
    console.log(s)
  }

  return (
    <View>
      <Text>UserListContainer</Text>
      <Button
        title="Load users"
        onPress={() => {
          loadNextPage()
          dispatch(loadAllUsers([
            {id: 1, email: 'xd', firstName: 'Bob', lastName: 'Hopf'},
            {id: 2, email: 'xd', firstName: 'Bob', lastName: 'Hopf'},
            {id: 3, email: 'xd', firstName: 'Bob', lastName: 'Hopf'},
          ]))
        }}
      />

      <Button title="action" onPress={() => addUser()}/>
    </View>
  );
}

export default UserListView;
