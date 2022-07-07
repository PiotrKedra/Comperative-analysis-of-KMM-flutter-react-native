import React from 'react';
import { Button, StyleSheet, View } from 'react-native';
import { Audio } from 'expo-av';
import { AUDIO_DETAILS_ROUTE } from '../../navigation/ROUTES';
import * as MediaLibrary from 'expo-media-library';


const RecordAudioView = ({navigation}) => {
  const [recording, setRecording] = React.useState();

  const startRecording = async () => {
    try {
      await Audio.requestPermissionsAsync();
      await Audio.setAudioModeAsync({
        allowsRecordingIOS: true,
        playsInSilentModeIOS: true,
      });
      const recording = new Audio.Recording();
      await recording.prepareToRecordAsync(
        Audio.RECORDING_OPTIONS_PRESET_LOW_QUALITY
      );
      await recording.startAsync();
      setRecording(recording);
      console.log('Recording started');
    } catch (err) {
      console.error('Failed to start recording', err);
    }
  }

  const stopRecording = async () => {
    console.log('Stopping recording..');
    await recording.stopAndUnloadAsync();
    const uri = recording.getURI();
    const asset = await MediaLibrary.createAssetAsync(uri);

    const album = await MediaLibrary.getAlbumAsync('Expo')
    await MediaLibrary.addAssetsToAlbumAsync(asset, album, true)

    navigation.navigate(AUDIO_DETAILS_ROUTE, { uri: uri })
    setRecording(undefined);
  }

  return (
    <View style={styles.container}>
      <Button
        title={recording ? 'Stop Recording' : 'Start Recording'}
        onPress={recording ? stopRecording : startRecording}
      />
    </View>
  );
}

export default RecordAudioView;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#ecf0f1',
    padding: 10,
  },
});
