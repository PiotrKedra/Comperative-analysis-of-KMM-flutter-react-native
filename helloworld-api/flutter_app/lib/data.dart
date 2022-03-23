class Data {
  final String value;
  final String valueClassification;
  final String timestamp;
  final String timeUntilUpdate;

  const Data({
    required this.value,
    required this.valueClassification,
    required this.timestamp,
    required this.timeUntilUpdate,
  });

  factory Data.fromJson(Map<String, dynamic> json) {
    return Data(
      value: json['value'],
      valueClassification: json['value_classification'],
      timestamp: json['timestamp'],
      timeUntilUpdate: json['time_until_update'],
    );
  }
}