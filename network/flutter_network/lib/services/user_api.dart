import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'package:flutter_network/models/user.dart';


const base_url = 'https://reqres.in/api/';

Future<List<User>> fetchUserList() async {
  final response = await http
      .get(Uri.parse(base_url + 'users'));
  return compute(pareUsers, response.body);
}

List<User> pareUsers(String jsonResponse) {
  final parsed = jsonDecode(jsonResponse)['data'].cast<Map<String, dynamic>>();
  return parsed.map<User>((json) => User.fromJson(json)).toList();
}

Future<List<User>> getUserListPage(int pageNr) async {
  final response = await http
      .get(Uri.parse(base_url + 'users?page=' + pageNr.toString()));
  return compute(pareUsers, response.body);
}