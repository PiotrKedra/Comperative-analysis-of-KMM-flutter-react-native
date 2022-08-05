import React from 'react';
import { Button, StyleSheet, Text, View } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';


export default function App() {

  const [json, setJson] = React.useState({});

  const [apiTimes, setApiTimes] = React.useState([]);
  const [saveTimes, setSaveTimes] = React.useState([]);
  const [readTimes, setReadTimes] = React.useState([]);


  const measureFetch = () => {
    const t1 = performance.now()
    fetch(`https://reqres.in/api/users/`)
      .then((response) => response.json())
      .then((json) => {
        setJson(json);
        const t2 = performance.now()
        const tmp = apiTimes
        tmp.push(t2-t1)
        setApiTimes(null)
        setApiTimes(tmp)
      })
  }

  const measureStorageSave = () => {
    const t1 = performance.now()
    AsyncStorage.setItem('@json', JSON.stringify(json))
      .then(() => {
        const t2 = performance.now()
        const tmp = saveTimes
        tmp.push(t2-t1)
        setSaveTimes(null)
        setSaveTimes(tmp)
      })
  }

  const measureStorageRead = () => {
    const t1 = performance.now()
    AsyncStorage.getItem('@json')
      .then(value => value != null ? JSON.parse(value) : null)
      .then((json) => {
        setJson(json);
        const t2 = performance.now()
        const tmp = readTimes
        tmp.push(t2-t1)
        setReadTimes(null)
        setReadTimes(tmp)
      })
  }


  return (
    <View style={styles.container}>
      <Text>{JSON.stringify(apiTimes)} milliseconds</Text>
      <Button onPress={() => measureFetch()} title="Measure fetch"/>

      <Text>{JSON.stringify(saveTimes)} milliseconds</Text>
      <Button onPress={() => measureStorageSave()} title="Measure storage save"/>

      <Text>{JSON.stringify(readTimes)} milliseconds</Text>
      <Button onPress={() => measureStorageRead()} title="Measure storage read"/>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
