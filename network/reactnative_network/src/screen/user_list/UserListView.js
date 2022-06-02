import React from 'react';
import { Button, Text, View } from 'react-native';
import { useDispatch, useSelector } from 'react-redux';
import { loadAllUsers } from '../../redux/userSlice';


const UserListView = () => {
  const userList = useSelector((state) => state.users)
  const dispatch = useDispatch()

  console.log('DUPA')
  console.log(userList)
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
