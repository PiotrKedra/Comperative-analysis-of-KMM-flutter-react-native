import React from 'react';
import { SafeAreaView, Text, Image, StyleSheet } from 'react-native';

const PictureDetailsView = ({navigation, route}) => {

  const base64 = route.params;

  console.log(route)

  return (
    <SafeAreaView style={styles.container}>
      <Image style={styles.image} source={{ uri: "data:image/jpg;base64," + base64 }}/>
    </SafeAreaView>
  );
}

export default PictureDetailsView;

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
