import 'package:flutter/material.dart';

class UserModificationScreen extends StatelessWidget {
  const UserModificationScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('User Modification Screen')
      ),
      body: GestureDetector(
        child: Image.network(
          'https://picsum.photos/250?image=9',
        ),
      ),
    );
  }
}