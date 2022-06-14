import 'package:flutter/material.dart';
import 'package:flutter_network/models/user.dart';
import 'package:flutter_network/providers/user_list_model.dart';
import 'package:flutter_network/screens/user_list/widgets/user_tile.dart';
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
  
  late Future<List<User>> futureUser;

  @override
  void initState() {
    super.initState();
    initUserList();
  }

  void initUserList() async {
    futureUser = fetchUserList();
    List<User> userList = await futureUser;
    Provider.of<UserListModel>(context, listen: false).init(userList);
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
      body: SingleChildScrollView(
        child: ListView (
          scrollDirection: Axis.vertical,
          physics: const NeverScrollableScrollPhysics(),
          shrinkWrap: true,
          children: [
            Consumer<UserListModel>(
              builder: (context, userListModel, child) {
                return UserList(users: userListModel.users);
              },
            ),
          ]
        ),
      )
    );
  }
}

class UserList extends StatelessWidget {
  const UserList({Key? key, required this.users}) : super(key: key);

  final List<User> users;

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      scrollDirection: Axis.vertical,
      physics: const NeverScrollableScrollPhysics(),
      shrinkWrap: true,
      itemCount: users.length,
      itemBuilder: (BuildContext context, int index) {
        return UserTile(user: users[index]);
      }
    );
  }
}