
import 'package:flutter/foundation.dart';
import 'package:flutter_network/models/user.dart';

class UserListModel extends ChangeNotifier {

  List<User> _users = [];

  List<User> get users => _users;

  void setUserList(List<User> users) {
    _users = users;
    notifyListeners();
  }
}