import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from '@react-navigation/native';
import UserList from '../screen/user_list';
import UserDetails from '../screen/user_details';
import UserModification from '../screen/user_modification';
import { USER_DETAILS_ROUTE, USER_LIST_ROUTE, USER_MODIFICATION_ROUTE } from './ROUTES';

const Stack = createNativeStackNavigator();

const Navigation = () => (
  <NavigationContainer>
    <Stack.Navigator>
      <Stack.Screen name={USER_LIST_ROUTE} component={UserList} />
      <Stack.Screen name={USER_DETAILS_ROUTE} component={UserDetails} />
      <Stack.Screen name={USER_MODIFICATION_ROUTE} component={UserModification} />
    </Stack.Navigator>
  </NavigationContainer>
);

export default Navigation;
