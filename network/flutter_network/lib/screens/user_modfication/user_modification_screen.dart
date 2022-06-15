import 'package:flutter/material.dart';
import 'package:flutter_network/models/user.dart';
import 'package:flutter_network/providers/user_list_model.dart';
import 'dart:math';

import 'package:flutter_network/services/user_api.dart';
import 'package:flutter_network/services/user_cache.dart';
import 'package:provider/provider.dart';


class UserModificationScreen extends StatefulWidget {
  const UserModificationScreen({Key? key, required this.user}) : super(key: key);

  final User? user;

  @override
  _UserModificationScreenState createState() => _UserModificationScreenState();
}

class _UserModificationScreenState extends State<UserModificationScreen> {

  final firstNameControler = TextEditingController();
  final lastNameControler = TextEditingController();
  final emailControler = TextEditingController();

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    firstNameControler.dispose();
    super.dispose();
  }

  void modifyUser() async {
    List<User> newUserList;
    if (widget.user != null) {
      newUserList = await updateUserList();

    } else {
      newUserList = await createUserList();
    }
    newUserList.sort((a, b) => a.userId.compareTo(b.userId));
    await UserSharedPreferencesUtils.putObjectList(newUserList);
    Provider.of<UserListModel>(context, listen: false).setUserList(newUserList);
    
    Navigator.of(context).pop();
    if (widget.user != null) {
      Navigator.of(context).pop();
    }
  }

  Future<List<User>> updateUserList() async {
    User user = User(
      userId: widget.user!.userId,
      firstName: firstNameControler.text,
      lastName: lastNameControler.text,
      email: emailControler.text,
      avatar: widget.user!.avatar
    );
    user = await updateUser(user);
    List<User> currentUserList = UserSharedPreferencesUtils.getObjectList();
    List<User> userList = currentUserList.where((i) => i.userId != user.userId).toList();
    userList.add(user);
    return userList;
  }

  Future<List<User>> createUserList() async {
    User user = User(
      userId: Random().nextInt(10000),
      firstName: firstNameControler.text,
      lastName: lastNameControler.text,
      email: emailControler.text,
      avatar: "https://cdn.pixabay.com/photo/2015/01/06/16/14/woman-590490_960_720.jpg"
    );
    user = await createUser(user);
    List<User> userList = UserSharedPreferencesUtils.getObjectList();
    userList.add(user);
    return userList;
  }

  @override
  Widget build(BuildContext context) {

    String userFirstName = "First Name";
    String userLastName = "Last Name";
    String userEmail = "Email";

    String title = "Create new user";

    if (widget.user != null) {
      userFirstName = widget.user!.firstName;
      userLastName = widget.user!.lastName;
      userEmail = widget.user!.email;
      title = "Update user";
      firstNameControler.text = userFirstName;
      lastNameControler.text = userLastName;
      emailControler.text = userEmail;
    }

    return Scaffold(
      appBar: AppBar(
        title: const Text('User Modification')
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(title),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
            child: TextField(
              controller: firstNameControler,
              decoration: InputDecoration(
                border: const OutlineInputBorder(),
                hintText: userFirstName,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
            child: TextField(
              controller: lastNameControler,
              decoration: InputDecoration(
                border: const OutlineInputBorder(),
                hintText: userLastName,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
            child: TextField(
              controller: emailControler,
              decoration: InputDecoration(
                border: const OutlineInputBorder(),
                hintText: userEmail,
              ),
            ),
          ),
        ]
      ),
      floatingActionButton: FloatingActionButton(
        // When the user presses the button, show an alert dialog containing
        // the text that the user has entered into the text field.
        onPressed: () => modifyUser(),
        tooltip: 'Show me the value!',
        child: const Icon(Icons.text_fields),
      ),
    );
  }
}