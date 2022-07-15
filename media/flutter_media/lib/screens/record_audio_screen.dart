import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_media/screens/record_video_screen/record_video_screen.dart';
import 'package:flutter_sound/flutter_sound.dart';
import 'package:gallery_saver/gallery_saver.dart';
import 'package:permission_handler/permission_handler.dart';

class RecordAudioScreen extends StatefulWidget {
  const RecordAudioScreen({Key? key}) : super(key: key);

  @override
  _RecordAudioState createState() => _RecordAudioState();
}

class _RecordAudioState extends State<RecordAudioScreen> {
  final recorder = FlutterSoundRecorder();

  String text = "record";
  bool isRecorderRdy = false;

  @override
  void initState() {
    super.initState();

    initRecorder();
  }

  @override
  void dispose() {
    recorder.closeRecorder();
    super.dispose();
  }

  Future initRecorder() async {
    final status = await Permission.microphone.request();


    if (status == PermissionStatus.granted) {
     await recorder.openRecorder();
    }

    isRecorderRdy = true;
  }

  Future record() async {
    if (!isRecorderRdy) return;
    text = "stop";
    print(text);
    await recorder.startRecorder(toFile: 'audio');
  }

  Future stop() async {
    if (!isRecorderRdy) return;
    text = "record";
    print(text);
    final path = await recorder.stopRecorder();
    final audioFile = File(path!);
    print('Record Path: $path');

    final route = MaterialPageRoute(
      fullscreenDialog: true,
      builder: (_) => VideoPage(filePath: path),
    );
    Navigator.push(context, route);
  }
  
  @override
  Widget build(BuildContext context) {
    return Center(
        child: Stack(
          alignment: Alignment.bottomCenter,
          children: [
            TextButton(
              style: ButtonStyle(
                foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
              ),
              onPressed: () async {
                if (recorder.isRecording) {
                  await stop();
                } else {
                  await record();
                }
              },
              child: Text(text),
            )
          ]
        )
    );
  }
}