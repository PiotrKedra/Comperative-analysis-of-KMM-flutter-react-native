import 'package:flutter/material.dart';
import 'package:flutter_network/models/user.dart';
import 'package:flutter_network/providers/user_list_model.dart';
import 'package:flutter_network/screens/user_list/widgets/user_tile.dart';
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
    );
  }
}

class UserList extends StatefulWidget {
  const UserList({Key? key, required this.users}) : super(key: key);

  final List<User> users;

  @override
  State<UserList> createState() => _UserListState();
}

class _UserListState extends State<UserList> {

  late ScrollController _scrollController;

  int _page = 1;
  bool _isLoading = false;

  @override
  void initState() {
    super.initState();
    _scrollController = ScrollController()..addListener(_loadNextPage);
  }

  void _loadNextPage() async {
    if (_isLoading == true) {
      return;
    }
    _isLoading = true;
    _page += 1;
    List<User> userListPage = await getUserListPage(_page);
    List<User> currentUserList = UserSharedPreferencesUtils.getObjectList();
    List<User> userList = currentUserList + userListPage;
    await UserSharedPreferencesUtils.putObjectList(userList);
    Provider.of<UserListModel>(context, listen: false).setUserList(userList);
    _isLoading = false;
  }

  @override
  void dispose() {
    _scrollController.removeListener(_loadNextPage);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: ListView.builder(
        controller: _scrollController,
        itemCount: widget.users.length,
        itemBuilder: (_, int index) {
          return UserTile(user: widget.users[index]);
        }
      )
    );
  }
}