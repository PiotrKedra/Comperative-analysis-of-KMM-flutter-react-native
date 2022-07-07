import React, { useEffect, useRef, useState } from 'react';
import { Camera } from 'expo-camera';
import { Button, StyleSheet, View } from 'react-native';
import { Audio } from 'expo-av';
import * as MediaLibrary from 'expo-media-library';
import { VIDEO_DETAILS_ROUTE } from '../../navigation/ROUTES';

const RecordVideoView = ({navigation}) => {
  const cameraRef = useRef()
  const [recording, setIsRecording] = useState(false)

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      await Audio.requestPermissionsAsync();
      await Audio.setAudioModeAsync({
        allowsRecordingIOS: true,
        playsInSilentModeIOS: true,
      });
    })()
  }, [])

  const startRecording = () => {
    cameraRef.current.recordAsync()
  }

  const handlePress = async () => {
    if (recording === true) {
      await cameraRef.current.stopRecording()
      setIsRecording(false)
    } else {
      setIsRecording(true)
      const video = await cameraRef.current.recordAsync()
      const asset = await MediaLibrary.createAssetAsync(video.uri);
      MediaLibrary.createAlbumAsync('Expo', asset)
        .then(() => {
          console.log('Album created!');
        })
        .catch(error => {
          console.log('err', error);
        });
      navigation.navigate(VIDEO_DETAILS_ROUTE, video)
    }
  }

  return (
    <Camera style={styles.container} ref={cameraRef}>
      <View style={styles.btnContainer}>
        <Button title={recording ? 'Stop' : 'Record'} onPress={handlePress}/>
      </View>
    </Camera>
  );
}

export default RecordVideoView;

const styles = StyleSheet.create({
  container: {
    height: '100%',
    justifyContent: 'flex-end'
  },
  btnContainer: {
    backgroundColor: '#fff',
    alignSelf: 'center',
    marginBottom: 40
  }
})
