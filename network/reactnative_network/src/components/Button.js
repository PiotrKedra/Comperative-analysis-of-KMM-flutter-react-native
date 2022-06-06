import React from 'react';
import { StyleSheet, Text, TouchableOpacity } from 'react-native';

const Button = ({onPress, text, isRed = true}) => (
  <TouchableOpacity
    style={[styles.container, isRed ? {backgroundColor: 'red'} : {}]}
    onPress={() => onPress()}>
    <Text
      style={[styles.text, isRed ? {color: 'white'} : {color: 'black'}]}
    >
      {text}
    </Text>
  </TouchableOpacity>
);

export default Button;

const styles = StyleSheet.create({
  container: {
    paddingHorizontal: 25,
    paddingVertical: 15,
    borderRadius: 2,
    marginRight: 20,
    marginTop: 20,
    marginBottom: 20,
  },
  text: {
    fontSize: 30
  }
})
