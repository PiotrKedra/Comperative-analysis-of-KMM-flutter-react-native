import 'dart:async';
import 'dart:io';

import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:flutter_media/screens/record_audio_screen.dart';
import 'package:flutter_media/screens/record_video_screen/record_video_screen.dart';
import 'package:flutter_media/screens/take_picture/take_picture_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreen();
}

class _HomeScreen extends State<HomeScreen> {

  _getMedia() async {
    print("Media");
  }

  @override
  void initState() {
    super.initState();
    _getMedia();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        title: const Text('Home')
      ),
      body: Column(
        children: [
          TextButton(
            style: ButtonStyle(
              foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
            ),
            onPressed: () async { 
              WidgetsFlutterBinding.ensureInitialized();
              final cameras = await availableCameras();
              final firstCamera = cameras.first;
              Navigator.push(
                context, 
                MaterialPageRoute(builder: (context) => TakePictureScreen(camera: firstCamera))
              );
            },
            child: const Text('Take Photo'),
          ),
          TextButton(
            style: ButtonStyle(
              foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
            ),
            onPressed: () {
              Navigator.push(
                context, 
                MaterialPageRoute(builder: (context) => const RecordVideoScreen())
              );
            },
            child: const Text('Record video'),
          ),
          TextButton(
            style: ButtonStyle(
              foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
            ),
            onPressed: () {
              Navigator.push(
                context, 
                MaterialPageRoute(builder: (context) => const RecordAudioScreen())
              );
            },
            child: const Text('Record audio'),
          )
        ]
      ),
    );
  }
}