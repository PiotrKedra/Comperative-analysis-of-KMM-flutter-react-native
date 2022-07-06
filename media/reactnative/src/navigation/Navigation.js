import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { MEDIA_LIST_ROUTE, PICTURE_DETAILS_SCREEN, TAKE_PICTURE_ROUTE } from './ROUTES';
import MediaListScreen from '../screens/media_list'
import PictureDetailsScreen from '../screens/picture_details'
import TakePictureScreen from '../screens/take_picture'


const Stack = createNativeStackNavigator()

const Navigation = () => (
  <NavigationContainer>
    <Stack.Navigator>
      <Stack.Screen name={MEDIA_LIST_ROUTE} component={MediaListScreen} options={{ title: 'Media list' }}/>
      <Stack.Screen name={TAKE_PICTURE_ROUTE} component={TakePictureScreen} options={{ title: 'Take picture' }}/>
      <Stack.Screen name={PICTURE_DETAILS_SCREEN} component={PictureDetailsScreen} options={{ title: 'Picture details' }}/>
    </Stack.Navigator>
  </NavigationContainer>
);

export default Navigation;
