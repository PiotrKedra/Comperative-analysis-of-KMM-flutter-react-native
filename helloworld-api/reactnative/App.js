/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import type {Node} from 'react';
import {
  Pressable,
  SafeAreaView,
  StatusBar,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';

const App: () => Node = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const [value, setValue] = React.useState('..');
  const [classification, setClassification] = React.useState('..');
  const [timestamp, setTimestamp] = React.useState('..');
  const [nextUpdate, setNextUpdate] = React.useState('..');

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'space-between',
    height: '100%',
    width: '100%',
    paddingTop: '25%',
    paddingHorizontal: 10,
    paddingBottom: 10,
    color: '#fff',
  };

  const btnStyle = {
    width: 160,
    height: 160,
    borderWidth: 1,
    borderColor: '#fff',
    borderRadius: 80,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <View style={backgroundStyle}>
        <Text style={[textStyle, {fontSize: 30, textAlign: 'center'}]}>
          Fear And Greed Index
        </Text>
        <Pressable style={btnStyle} onPress={() => console.log('SHIT')}>
          <Text style={[textStyle, {fontSize: 60, textAlign: 'center'}]}>
            {value}
          </Text>
        </Pressable>
        <View style={{width: '100%'}}>
          <InfoRow attribute="Value" value={value} />
          <InfoRow attribute="Classification" value={classification} />
          <InfoRow attribute="Timestamp" value={timestamp} />
          <InfoRow attribute="Next update" value={nextUpdate} />
        </View>
      </View>
    </SafeAreaView>
  );
};

const rowStyle = {
  display: 'flex',
  flexDirection: 'row',
  width: '100%',
  justifyContent: 'space-between',
  marginBottom: 10,
};

const textStyle = {
  fontFamily: 'monospace',
  color: '#fff',
  fontSize: 16,
};

const InfoRow = ({attribute, value}) => (
  <View style={rowStyle}>
    <Text style={[textStyle, {color: '#AFAFAF'}]}>{attribute}</Text>
    <Text style={textStyle}>{value}</Text>
  </View>
);

export default App;
