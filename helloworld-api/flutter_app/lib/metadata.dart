class Metadata {
  final String error;

  const Metadata({
    required this.error,
  });

  factory Metadata.fromJson(Map<String, dynamic> json) {
    return Metadata(
      error: json['error'],
    );
  }
}