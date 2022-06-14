import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_network/models/user.dart';
import 'package:shared_preferences/shared_preferences.dart';


class UserSharedPreferencesUtils {
  static late SharedPreferences prefs;

  static const String userListKey = "userListKey";

  static init() async {
    prefs = await SharedPreferences.getInstance();
  }

  static Future<bool> putObjectList(List<User> list) async {
    List<String> _dataList = list.map((value) {
      return json.encode(value);
    }).toList();
    return prefs.setStringList(userListKey, _dataList);
  }

  static List<User> getObjectList() {
    List<String>? dataList = prefs.getStringList(userListKey);

    if (dataList == null) return [];

    return dataList.map((value) {
      User _dataMap = User.fromJson(json.decode(value));
      return _dataMap;
    }).toList();
  }


}