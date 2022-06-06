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
      <Stack.Screen name={USER_LIST_ROUTE} component={UserList} options={{ title: 'User List' }}/>
      <Stack.Screen name={USER_DETAILS_ROUTE} component={UserDetails} options={{ title: 'User Details' }}/>
      <Stack.Screen name={USER_MODIFICATION_ROUTE} component={UserModification} options={{ title: 'Modify User' }}/>
    </Stack.Navigator>
  </NavigationContainer>
);

export default Navigation;
