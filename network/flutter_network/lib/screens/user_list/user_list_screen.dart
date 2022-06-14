import 'package:flutter/material.dart';
import 'package:flutter_network/models/user.dart';
import 'package:flutter_network/providers/user_list_model.dart';
import 'package:flutter_network/screens/user_details/user_details_screen';
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
            child: const Text('go to user details')
          ),
           GestureDetector(
            onTap: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return const UserModificationScreen();
              }));
            },
            child: const Text('got to use modification')
          ),
          Consumer<UserListModel>(
            builder: (context, userListModel, child) {
              return Text('Number of users ${userListModel.users.length}');
            },
          ),
          FutureBuilder<List<User>>(
            future: futureUser,
            builder: (context, snapshot) {
              if (snapshot.hasError) {
                return const Center(
                  child: Text('An error has occurred!'),
                );
              } else if (snapshot.hasData) {
                return UserList(users: snapshot.data!);
              } else {
                return const Center(
                  child: CircularProgressIndicator(),
                );
              }
            },
          ),
        ]
      ),
    );
  }
}

class UserList extends StatelessWidget {
  const UserList({Key? key, required this.users}) : super(key: key);

  final List<User> users;

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      scrollDirection: Axis.vertical,
      shrinkWrap: true,
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
      ),
      itemCount: users.length,
      itemBuilder: (context, index) {
        return Image.network(users[index].avatar);
      },
    );
  }
}