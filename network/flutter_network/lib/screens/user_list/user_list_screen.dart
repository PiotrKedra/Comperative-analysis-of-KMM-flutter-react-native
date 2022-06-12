import 'package:flutter/material.dart';
import 'package:flutter_network/screens/user_details/user_details_screen';
import 'package:flutter_network/screens/user_modfication/user_modification_screen.dart';

class UserListScreen extends StatelessWidget {
  const UserListScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('User List')
      ),
      body: Column (
        children: [
           GestureDetector(
            onTap: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return const UserDetailsScreen();
              }));
            },
            child: Image.network(
              'https://picsum.photos/250?image=9',
            ),
          ),
           GestureDetector(
            onTap: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return const UserModificationScreen();
              }));
            },
            child: Image.network(
              'https://picsum.photos/250?image=2',
            ),
          ),
        ]
      ),
    );
  }
}