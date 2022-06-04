import React from 'react';
import Navigation from './navigation/Navigation';
import { Provider } from 'react-redux'
import { store } from './redux/store';
import Toast from 'react-native-toast-message';

export default () => (
  <Provider store={store}>
    <Toast />
    <Navigation/>
  </Provider>
)
