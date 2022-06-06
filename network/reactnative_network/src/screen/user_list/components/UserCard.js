import React from 'react';
import { View, Image, Text, StyleSheet, TouchableOpacity } from 'react-native';
import { USER_DETAILS_ROUTE } from '../../../navigation/ROUTES';

const UserCard = ({user, navigation}) => {
  return (
    <TouchableOpacity
      style={styles.container}
      onPress={() => navigation.navigate(USER_DETAILS_ROUTE, user)}
    >
      <Image
        style={styles.avatar}
        source={{uri: user.avatar}}
      />
      <View style={styles.textContainer}>
        <Text style={styles.nameText}>{`${user.first_name} ${user.last_name}`}</Text>
        <Text style={styles.emailText}>{user.email}</Text>
        <Text>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</Text>
      </View>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'row',
    paddingHorizontal: 20,
    paddingVertical: 20,
  },
  avatar: {
    width: 140,
    height: 140,
    borderRadius: 30
  },
  textContainer: {
    flex: 1,
    flexDirection: 'column',
    marginLeft: 10
  },
  nameText: {
    fontSize: 25,
    fontWeight: '600'
  },
  emailText: {
    color: 'gray',
  },
  item: {
    padding: 20,
    marginVertical: 8,
    marginHorizontal: 16,
  },

});

export default UserCard;
