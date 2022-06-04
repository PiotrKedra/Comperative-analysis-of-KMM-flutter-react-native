import React from 'react';
import { Button, Text, View } from 'react-native';
import { useDispatch, useSelector } from 'react-redux';
import { loadAllUsers } from '../../redux/userSlice';
import { createUser, deleteUser, getUserById, getUsers, updateUser } from '../../service/userApi';


const UserListView = () => {
  const userList = useSelector((state) => state.users)
  const dispatch = useDispatch()

  console.log('DUPA')
  console.log(userList)

  React.useEffect(() => {
    loadUsers()
  }, [])

  const loadUsers = async () => {
    const users = await getUsers()
    console.log("GET ALL")
    console.log(users)

    const getUser = await getUserById(1)
    console.log("GET BY ID")
    console.log(getUser)

    const create = await createUser({
      email: 'DUPDA',
      firstName: "BOB",
      lastName: "Chill"
    })
    console.log("CREATE")
    console.log(create)

    const update = await updateUser({
      id: '1',
      email: 'DUPDA',
      firstName: "BOB",
      lastName: "Chill"
    })
    console.log("update")
    console.log(update)

    const deleteU = await deleteUser(1)
    console.log("DELETE")
    console.log(deleteU)
  }

  return (
    <View>
      <Text>UserListContainer</Text>
      <Button
        title="Load users"
        onPress={() => {
          dispatch(loadAllUsers([
            {id: 1, email: 'xd', firstName: 'Bob', lastName: 'Hopf'},
            {id: 2, email: 'xd', firstName: 'Bob', lastName: 'Hopf'},
            {id: 3, email: 'xd', firstName: 'Bob', lastName: 'Hopf'},
          ]))
        }}
      />
    </View>
  );
}

export default UserListView;
