import React from 'react';
import { Button, Text, View } from 'react-native';
import { TAKE_PICTURE_ROUTE } from '../../navigation/ROUTES';

const MediaListView = ({navigation}) => (
  <View>
    <Text>list</Text>
    <Button title="Take picture" onPress={() => navigation.navigate(TAKE_PICTURE_ROUTE)}/>
  </View>
);

export default MediaListView;
