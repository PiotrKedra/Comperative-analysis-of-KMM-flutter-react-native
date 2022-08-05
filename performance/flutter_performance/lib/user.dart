class User {
  final int userId;
  final String firstName;
  final String lastName;
  final String email;
  final String avatar;


  const User({
    required this.userId,
    required this.firstName,
    required this.lastName,
    required this.email,
    required this.avatar,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      userId: json['id'],
      firstName: json['first_name'],
      lastName: json['last_name'],
      email: json['email'],
      avatar: json['avatar'],
    );
  }

  Map<String, dynamic> toJson() => {
    "id": userId, 
    "first_name": firstName,
    "last_name": lastName,
    "email": email,
    "avatar": avatar
  };

  @override
  String toString() {
    return "userId: $userId, email: $email";
  }
}