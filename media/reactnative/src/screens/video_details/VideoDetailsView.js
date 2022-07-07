import React from 'react';
import { Button, SafeAreaView, StyleSheet, View } from 'react-native';
import { Video } from 'expo-av';


const VideoDetailsView = ({route}) => {

  const uri = route.params.uri;

  const video = React.useRef(null);
  const [status, setStatus] = React.useState({});

  return (
    <SafeAreaView style={styles.container}>
      <Video
        ref={video}
        style={styles.image}
        source={{
          uri: uri
        }}
        useNativeControls
        resizeMode="contain"
        isLooping
        onPlaybackStatusUpdate={status => setStatus(() => status)}
      >
      </Video>
      <View style={styles.buttons}>
        <Button
          title={status.isPlaying ? 'Pause' : 'Play'}
          onPress={() =>
            status.isPlaying ? video.current.pauseAsync() : video.current.playAsync()
          }
        />
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  },
  image: {
    alignSelf: 'stretch',
    flex: 1
  }
})

export default VideoDetailsView;
