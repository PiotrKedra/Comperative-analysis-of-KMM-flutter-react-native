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

Future<User> createUser(User user) async {
  await http.post(
    Uri.parse(base_url + 'users'), 
    body: jsonEncode(<String, String>{
      'name': user.firstName,
      'job': user.lastName
    })
  );
  return user;
}

Future<User> updateUser(User user) async {
  await http.put(
    Uri.parse(base_url + 'users'), 
    body: jsonEncode(<String, String>{
      'name': user.firstName,
      'job': user.lastName
    })
  );
  return user;
}

void deleteUser(User user) async {
  await http.delete(Uri.parse(base_url + 'users/' + user.userId.toString()));
}