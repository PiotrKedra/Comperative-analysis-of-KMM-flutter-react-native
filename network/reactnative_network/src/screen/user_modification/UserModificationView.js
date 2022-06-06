import React from 'react';
import { StyleSheet, TextInput, View, Text } from 'react-native';
import Button from '../../components/Button';
import { createUser, updateUser } from '../../service/userService';
import { useDispatch } from 'react-redux';
import { saveUserList } from '../../redux/userSlice';

const UserModificationView = ({route, navigation}) => {
  const dispatch = useDispatch()

  const user = route.params

  const [email, setEmail] = React.useState('')
  const [firstName, setFirstName] = React.useState('')
  const [lastName, setLastName] = React.useState('')

  React.useEffect(() => {
    if(user !== undefined) {
      setEmail(user.email)
      setFirstName(user.first_name)
      setLastName(user.last_name)
    }
  }, [])

  const submit = async () => {
    if (user === undefined) {
      const userList = await createUser({
        email: email,
        first_name: firstName,
        last_name: lastName,
        avatar: 'https://i.pinimg.com/564x/2a/b1/d8/2ab1d88518534e24a7138da3d4bae4be.jpg'
      })
      dispatch(saveUserList(userList))
      navigation.goBack()
    } else {
      const userList = await updateUser({
        id: user.id,
        email: email,
        first_name: firstName,
        last_name: lastName,
        avatar: user.avatar
      })
      dispatch(saveUserList(userList))
      navigation.popToTop()
    }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Create user</Text>
      <TextInput
        style={styles.input}
        onChangeText={setEmail}
        value={email}
        placeholder="Email"
      />
      <TextInput
        style={styles.input}
        onChangeText={setFirstName}
        value={firstName}
        placeholder="First Name"
      />
      <TextInput
        style={styles.input}
        onChangeText={setLastName}
        value={lastName}
        placeholder="Last Name"
      />
      <Button
        onPress={() => submit()}
        text='Submit'
      />
    </View>
  );
}

export default UserModificationView;

const styles = StyleSheet.create({
  container: {
    width: '85%',
    marginLeft: '5%'
  },
  title: {
    marginTop: 40,
    fontSize: 35,
    fontWeight: '600'
  },
  input: {
    margin: 10,
    marginLeft: 0,
    padding: 20,
    fontSize: 25,
    borderColor: '#ddd',
    borderWidth: 1,
    borderRadius: 2,
  },
})
