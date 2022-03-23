import 'package:flutter/material.dart';
import 'package:flutter_app/fear_and_greed_index.dart';
import 'package:flutter_app/info.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        scaffoldBackgroundColor: const Color(0x07090DFF)
      ),
      home: const MyHomePage(title: 'Flutter Hello World'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  late Future<FearAndGreedIndex> futureAlbum;

  @override
  void initState() {
    super.initState();
    futureAlbum = fetchAlbum();
  }

  Future<FearAndGreedIndex> fetchAlbum() async {
    final response = await http
        .get(Uri.parse('https://api.alternative.me/fng/'));

    if (response.statusCode == 200) {
      return FearAndGreedIndex.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to load album');
    }
  }

  void _incrementCounter() {
    setState(() {
      futureAlbum = fetchAlbum();
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Padding(
        padding: const EdgeInsets.only(top: 200.0, left: 20, right: 20),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text('Fear And Gread Index',style: TextStyle(color: Color(0xffffffff),fontSize: 35,)),
              const SizedBox(height: 60),
              ElevatedButton(
                child: FutureBuilder<FearAndGreedIndex>(
                  future: futureAlbum,
                  builder: (context, snapshot) {
                    if (snapshot.hasData) {
                      return Text(
                        snapshot.data!.data.value, 
                        style: const TextStyle(
                          color: Color(0xffffffff),
                          fontSize: 70,
                          fontWeight: FontWeight.w200,
                        )
                      );
                    }
                    return const CircularProgressIndicator();
                  },
                ),
                style: ElevatedButton.styleFrom(
                  shape: const CircleBorder(
                    side: BorderSide(
                      color: Colors.white, 
                      width: 1,
                    ),
                  ),
                  padding: const EdgeInsets.all(40),
                  primary: Colors.transparent,
                ),
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => const EsteerEgg()),
                  );
                },
              ),
              const Spacer(),
              FutureBuilder<FearAndGreedIndex>(
                future: futureAlbum,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return Info(data: snapshot.data!.data);
                  }
                  return const CircularProgressIndicator();
                },
              )
            ],
          ),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}


class EsteerEgg extends StatelessWidget {
  const EsteerEgg({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Easter egg'),
      ),
      body: const Center(
        child: Text('Hello Wordl!', style: TextStyle(color: Color(0xffffffff),fontSize: 25,))
      ),
    );
  }
}