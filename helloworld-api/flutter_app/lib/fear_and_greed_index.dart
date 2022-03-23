import 'package:flutter_app/data.dart';
// import 'package:flutter_app/metadata.dart';

class FearAndGreedIndex {
  final String name;
  final Data data;
  // final Metadata metadata;

  const FearAndGreedIndex({
    required this.name,
    required this.data,
    // required this.metadata,
  });

  factory FearAndGreedIndex.fromJson(Map<String, dynamic> json) {
    return FearAndGreedIndex(
      name: json['name'],
      data: Data.fromJson(json['data'][0]),
      // metadata: Metadata.fromJson(json['metadata']),
    );
  }
}