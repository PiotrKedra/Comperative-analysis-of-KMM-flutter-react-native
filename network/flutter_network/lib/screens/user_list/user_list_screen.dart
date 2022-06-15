import 'package:flutter/material.dart';
import 'package:flutter_network/models/user.dart';
import 'package:flutter_network/providers/user_list_model.dart';
import 'package:flutter_network/screens/user_list/widgets/user_list.dart';
import 'package:flutter_network/screens/user_modfication/user_modification_screen.dart';
import 'package:flutter_network/services/user_api.dart';
import 'package:flutter_network/services/user_cache.dart';
import 'package:provider/provider.dart';

class UserListScreen extends StatefulWidget {
  const UserListScreen({key}) : super(key: key);

  @override
  State<UserListScreen> createState() => _UserListScreenState();
}

class _UserListScreenState extends State<UserListScreen> {

  @override
  void initState() {
    super.initState();
    initUserList();
  }

  void initUserList() async {
    List<User> userList = await fetchUserList();
    Provider.of<UserListModel>(context, listen: false).setUserList(userList);
    await UserSharedPreferencesUtils.putObjectList(userList);
    print(UserSharedPreferencesUtils.getObjectList());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        title: const Text('User List')
      ),
      body: Column(
        children: [
          Consumer<UserListModel>(
            builder: (context, userListModel, child) {
              return UserList(users: userListModel.users);
            },
          ),
        ]
      ),
      floatingActionButton: FloatingActionButton(
        // When the user presses the button, show an alert dialog containing
        // the text that the user has entered into the text field.
        onPressed: () => {
          Navigator.push(
            context, 
            MaterialPageRoute(builder: (context) => const UserModificationScreen(user: null))
          )
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}