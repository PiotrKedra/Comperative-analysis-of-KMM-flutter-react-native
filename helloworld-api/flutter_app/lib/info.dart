import 'package:flutter/material.dart';
import 'package:flutter_app/data.dart';


class Info extends StatefulWidget {
  const Info({
    Key? key, 
    required this.data, 
  }) : super(key: key);

  final Data data;

  @override
  State<StatefulWidget> createState() => _Info();
}

class _Info extends State<Info> {

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        RowInfo(name: 'Value', value: widget.data.value),
        const SizedBox(height: 15),
        RowInfo(name: 'Classification', value: widget.data.valueClassification),
        const SizedBox(height: 15),
        RowInfo(name: 'Timestamp', value: widget.data.timestamp),
        const SizedBox(height: 15),
        RowInfo(name: 'Next update', value: widget.data.timeUntilUpdate),
        const SizedBox(height: 60),
      ]
    );
  }
}

class RowInfo extends StatefulWidget {
  const RowInfo({Key? key, required this.name, required this.value}) : super(key: key);

  final String name;
  final String value;

  @override
  State<StatefulWidget> createState() => _RowInfo();
}

class _RowInfo extends State<RowInfo> {

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Text(widget.name, style: const TextStyle(color: Color(0xffAFAFAF), fontSize: 20)),
        const Spacer(),
        Text(widget.value, style: const TextStyle(color: Colors.white, fontSize: 20)),
      ],
    );
  }
}