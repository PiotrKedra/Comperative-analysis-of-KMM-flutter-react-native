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

const App: () => Node = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const [value, setValue] = React.useState('..');
  const [classification, setClassification] = React.useState('..');
  const [timestamp, setTimestamp] = React.useState('..');
  const [nextUpdate, setNextUpdate] = React.useState('..');

  const [isHello, setIsHello] = React.useState(false);

  React.useEffect(() => {
    fetch('https://api.alternative.me/fng/')
      .then(response => response.json())
      .then(json => {
        setValue(json.data[0].value);
        setClassification(json.data[0].value_classification);
        setTimestamp(json.data[0].timestamp);
        setNextUpdate(json.data[0].time_until_update);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  const backgroundStyle = {
    backgroundColor: '#07090D',
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
      {isHello ? (
        <View
          style={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            width: '100%',
            height: '100%',
          }}>
          <Text style={textStyle}>Hello World!</Text>
        </View>
      ) : (
        <View style={backgroundStyle}>
          <Text style={[textStyle, {fontSize: 30, textAlign: 'center'}]}>
            Fear And Greed Index
          </Text>
          <Pressable style={btnStyle} onPress={() => setIsHello(true)}>
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
      )}
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
