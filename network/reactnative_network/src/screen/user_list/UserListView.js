import React from 'react';
import { FlatList, View } from 'react-native';
import { useDispatch, useSelector } from 'react-redux';
import { saveUserList } from '../../redux/userSlice';
import UserCard from './components/UserCard';
import { loadNextPageUserList, loadUserList } from '../../service/userService';
import UserListSeparator from './components/UserListSeparator';
import Button from '../../components/Button';
import { USER_MODIFICATION_ROUTE } from '../../navigation/ROUTES';


const UserListView = ({navigation}) => {
  const userList = useSelector((state) => state.users)
  const dispatch = useDispatch()

  const [page, setPage] = React.useState(1)

  React.useEffect(() => {
    initUserList()
  }, [])

  const initUserList = async () => {
    const users = await loadUserList()
    dispatch(saveUserList(users))
  }

  const loadNextPage = async () => {
    const users = await loadNextPageUserList(page + 1)
    setPage(page + 1)
    dispatch(saveUserList(users))
  }

  return (
    <View style={{ width: '100%'}}>
      <FlatList
        data={userList}
        renderItem={({item}) => <UserCard user={item} navigation={navigation}/>}
        onEndReachedThreshold={0.5}
        onEndReached={() => loadNextPage()}
        ItemSeparatorComponent={() => <UserListSeparator/>}
      />
      <View style={{position: 'absolute', bottom: 20, right: 20}}>
        <Button
          onPress={() => navigation.navigate(USER_MODIFICATION_ROUTE)}
          text='Add'
        />
      </View>
    </View>
  );
}

export default UserListView;
