import React from 'react';
import { Image, StyleSheet, Text, View } from 'react-native';
import Button from '../../components/Button';
import { deleteUser } from '../../service/userService';
import { useDispatch } from 'react-redux';
import { saveUserList } from '../../redux/userSlice';
import { USER_MODIFICATION_ROUTE } from '../../navigation/ROUTES';

const UserDetailsView = ({route, navigation}) => {
  const dispatch = useDispatch()
  const user = route.params

  const removeUser = async () => {
    const userList = await deleteUser(user.id)
    dispatch(saveUserList(userList))
    navigation.goBack()
  }

  return (
    <View style={styles.container}>
      <Text style={styles.nameText}>{`${user.first_name} ${user.last_name}`}</Text>
      <Image
        style={styles.avatar}
        source={{uri: user.avatar}}
      />
      <Text style={styles.emailText}>{user.email}</Text>
      <Text style={styles.descriptionText}>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</Text>
      <View style={styles.buttonCtn}>
        <Button
          onPress={() => removeUser()}
          text='Delete'
          isRed={false}
        />
        <Button
          onPress={() => navigation.navigate(USER_MODIFICATION_ROUTE, user)}
          text='Update'
        />
      </View>
    </View>
  );
}

export default UserDetailsView;

const styles = StyleSheet.create({
  container: {
    width: '65%',
    marginLeft: '5%'
  },
  nameText: {
    fontSize: 35,
    fontWeight: '600',
    marginTop: 40,
  },
  avatar: {width: 280, height: 280, marginTop: 20, borderRadius: 50},
  emailText: {
    color: 'gray',
    fontSize: 25,
    marginTop: 20,
  },
  descriptionText: { fontSize: 25, marginTop: 10 },
  buttonCtn: {
    flexDirection: 'row'
  }
})
