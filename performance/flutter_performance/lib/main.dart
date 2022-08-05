import 'package:flutter/material.dart';
import 'package:flutter_performance/api.dart';
import 'package:flutter_performance/shared_pref.dart';
import 'package:flutter_performance/user.dart';

void main() {
  AppConfig.init(() {
    runApp(const MyApp());
  });
}

class AppConfig {
  static Future init(VoidCallback callback) async {
    WidgetsFlutterBinding.ensureInitialized();
    await UserSharedPreferencesUtils.init();
    callback();
  }
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
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
  int _executionApi = 0;
  int _readTime = 0;
  int _saveTime = 0;

  List<User> _json = [];


  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  void fetch() async {
    Stopwatch stopwatch = new Stopwatch()..start();
    List<User> tmp = await fetchUserList();
    setState(() {
      _json = tmp;
    });
    final x = stopwatch.elapsedMilliseconds;
    setState(() {
      _executionApi = x;
    });
  }

  void saveData() async {
    Stopwatch stopwatch = new Stopwatch()..start();
    await UserSharedPreferencesUtils.putObjectList(_json);
    final x = stopwatch.elapsedMilliseconds;
    setState(() {
      _saveTime = x;
    });
  }

  void readData() async {
    Stopwatch stopwatch = new Stopwatch()..start();
    List<User> tmp = UserSharedPreferencesUtils.getObjectList();
    setState(() {
      _json = tmp;
    });
    final x = stopwatch.elapsedMilliseconds;
    setState(() {
      _readTime = x;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('$_executionApi miliseconds'),
            TextButton(
              onPressed: () { fetch(); },
              child: const Text('Measure fetch api'),
            ),
            Text('$_saveTime miliseconds'),
            TextButton(
              onPressed: () { saveData(); },
              child: const Text('Measure save'),
            ),
            Text('$_readTime miliseconds'),
            TextButton(
              onPressed: () { readData(); },
              child: const Text('Measure read'),
            ),
          ],
        ),
      ),
    );
  }
}
