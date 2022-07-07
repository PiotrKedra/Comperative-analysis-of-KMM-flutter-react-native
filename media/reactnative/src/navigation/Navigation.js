import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import {
  AUDIO_DETAILS_ROUTE,
  MEDIA_LIST_ROUTE,
  PICTURE_DETAILS_ROUTE, RECORD_AUDIO_ROUTE,
  RECORD_VIDEO_ROUTE,
  TAKE_PICTURE_ROUTE,
  VIDEO_DETAILS_ROUTE
} from './ROUTES';
import MediaListScreen from '../screens/media_list'
import PictureDetailsScreen from '../screens/picture_details'
import TakePictureScreen from '../screens/take_picture'
import RecordVideoScreen from '../screens/record_video'
import VideoDetailsScreen from '../screens/video_details'
import RecordAudioScreen from '../screens/record_audio'
import AudioDetailsScreen from '../screens/audio_details'


const Stack = createNativeStackNavigator()

const Navigation = () => (
  <NavigationContainer>
    <Stack.Navigator>
      <Stack.Screen name={MEDIA_LIST_ROUTE} component={MediaListScreen} options={{ title: 'Media list' }}/>
      <Stack.Screen name={TAKE_PICTURE_ROUTE} component={TakePictureScreen} options={{ headerShown: false }}/>
      <Stack.Screen name={PICTURE_DETAILS_ROUTE} component={PictureDetailsScreen} options={{ title: 'Picture details' }}/>
      <Stack.Screen name={RECORD_VIDEO_ROUTE} component={RecordVideoScreen} options={{ headerShown: false }}/>
      <Stack.Screen name={VIDEO_DETAILS_ROUTE} component={VideoDetailsScreen} options={{ title: 'Video details' }}/>
      <Stack.Screen name={RECORD_AUDIO_ROUTE} component={RecordAudioScreen} options={{ headerShown: false }}/>
      <Stack.Screen name={AUDIO_DETAILS_ROUTE} component={AudioDetailsScreen} options={{ title: 'Audio details' }}/>
    </Stack.Navigator>
  </NavigationContainer>
);

export default Navigation;
