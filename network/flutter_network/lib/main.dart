import 'package:flutter/material.dart';
import 'package:flutter_network/providers/user_list_model.dart';
import 'package:flutter_network/screens/user_list/user_list_screen.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (context) => UserListModel(),
      child: const MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Fluter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const UserListScreen(),
    );
  }
}