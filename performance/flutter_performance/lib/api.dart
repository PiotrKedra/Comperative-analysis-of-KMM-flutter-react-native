import 'package:flutter/foundation.dart';
import 'package:flutter_performance/user.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';


Future<void> xd() async {
  await http
      .get(Uri.parse('https://reqres.in/api/users'));
}

Future<List<User>> fetchUserList() async {
  final response = await http
      .get(Uri.parse('https://reqres.in/api/users'));
  return compute(pareUsers, response.body);
}

List<User> pareUsers(String jsonResponse) {
  final parsed = jsonDecode(jsonResponse)['data'].cast<Map<String, dynamic>>();
  return parsed.map<User>((json) => User.fromJson(json)).toList();
}