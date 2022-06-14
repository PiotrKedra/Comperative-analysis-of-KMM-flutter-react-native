import 'package:flutter/material.dart';
import 'package:flutter_network/models/user.dart';


class UserModificationScreen extends StatefulWidget {
  const UserModificationScreen({Key? key, required this.user}) : super(key: key);

  final User? user;

  @override
  _UserModificationScreenState createState() => _UserModificationScreenState();
}

class _UserModificationScreenState extends State<UserModificationScreen> {

  final firstNameControler = TextEditingController();
  final lastNameControler = TextEditingController();
  final emailControler = TextEditingController();

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    firstNameControler.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {

    String userFirstName = "First Name";
    String userLastName = "Last Name";
    String userEmail = "Email";

    String title = "Create new user";

    if (widget.user != null) {
      userFirstName = widget.user!.firstName;
      userLastName = widget.user!.lastName;
      userEmail = widget.user!.email;
      title = "Update user";
    }

    return Scaffold(
      appBar: AppBar(
        title: const Text('User Modification')
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(title),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
            child: TextField(
              controller: firstNameControler,
              decoration: InputDecoration(
                border: const OutlineInputBorder(),
                hintText: userFirstName,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
            child: TextField(
              controller: lastNameControler,
              decoration: InputDecoration(
                border: const OutlineInputBorder(),
                hintText: userLastName,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
            child: TextField(
              controller: emailControler,
              decoration: InputDecoration(
                border: const OutlineInputBorder(),
                hintText: userEmail,
              ),
            ),
          ),
        ]
      ),
      floatingActionButton: FloatingActionButton(
        // When the user presses the button, show an alert dialog containing
        // the text that the user has entered into the text field.
        onPressed: () {
          showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                // Retrieve the text the that user has entered by using the
                // TextEditingController.
                content: Column(
                  children: [
                    Text(firstNameControler.text),
                    Text(lastNameControler.text),
                    Text(emailControler.text),
                  ],
                )
              );
            },
          );
        },
        tooltip: 'Show me the value!',
        child: const Icon(Icons.text_fields),
      ),
    );
  }
}