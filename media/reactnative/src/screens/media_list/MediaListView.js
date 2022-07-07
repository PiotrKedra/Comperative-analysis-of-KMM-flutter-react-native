import React, { useEffect, useState } from 'react';
import { Button, FlatList, Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import {
  AUDIO_DETAILS_ROUTE,
  PICTURE_DETAILS_ROUTE, RECORD_AUDIO_ROUTE,
  RECORD_VIDEO_ROUTE,
  TAKE_PICTURE_ROUTE,
  VIDEO_DETAILS_ROUTE
} from '../../navigation/ROUTES';
import * as MediaLibrary from 'expo-media-library'
import { Dimensions } from 'react-native';


const MediaListView = ({navigation}) => {

  const [assets, setAssets] = useState([])

  useEffect(() => {
    (async () => {
      const album = await MediaLibrary.getAlbumAsync('Expo')
      const assets = await MediaLibrary.getAssetsAsync({
        album: album,
        first: 100,
        sortBy: ['creationTime'],
        mediaType: ['video', 'photo', 'audio']
      })
      setAssets(assets.assets)

      console.log("DUPA")
      console.log(album)
      console.log(assets)
    })()
  }, [])
  return (
    <View>
      <Button title="Take picture" onPress={() => navigation.navigate(TAKE_PICTURE_ROUTE)}/>
      <Button title="Record video" onPress={() => navigation.navigate(RECORD_VIDEO_ROUTE)}/>
      <Button title="Record audio" onPress={() => navigation.navigate(RECORD_AUDIO_ROUTE)}/>
      <FlatList
        data={assets}
        renderItem={({item}) => {
          if (item.mediaType === 'photo') {
            return (
              <TouchableOpacity style={styles.assetCtn} onPress={() => navigation.navigate(PICTURE_DETAILS_ROUTE, item)} >
                <Image source={{ uri: item.uri }} style={styles.asset}/>
                <Text>IMAGE</Text>
              </TouchableOpacity>
            )
          } else if (item.mediaType === 'video') {
            return (
              <TouchableOpacity style={styles.assetCtn} onPress={() => navigation.navigate(VIDEO_DETAILS_ROUTE, item)} >
                <Image source={{ uri: item.uri }} style={styles.asset}/>
                <Text>VIDEO</Text>
              </TouchableOpacity>
            )
          } else if (item.mediaType === 'audio') {
            return (
              <TouchableOpacity style={styles.assetCtn} onPress={() => navigation.navigate(AUDIO_DETAILS_ROUTE, item)} >
                <View style={{width: 50, height: 50, backgroundColor: 'red'}}/>
                <Text>AUDIO</Text>
              </TouchableOpacity>
            )
          }
        }}
      />
    </View>
  );
}

export default MediaListView;

const styles = StyleSheet.create({
  assetCtn: {margin: 15, marginBottom: 0, backgroundColor: 'lightgray' },
  asset: {
    width: Dimensions.get('window').width - 30,
    height: Dimensions.get('window').width/2,
    resizeMode: 'cover',
  },
})
