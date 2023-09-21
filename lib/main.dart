import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:gleap_sdk/gleap_sdk.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((timeStamp) async {
      // Enable this function to reproduce the issue - be sure to rerun the project
      // await initGleap();
    });
  }

  Future initGleap() async {
    await Gleap.initialize(
      token: 'USE YOUR OWN',
    );
    await Gleap.setLanguage(language: 'de');
    await Gleap.showFeedbackButton(true);
    await Gleap.setDisableInAppNotifications(disable: true);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      home: Scaffold(
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              FilledButton(
                child: const Text('Start new Activity'),
                onPressed: () async {
                  await const MethodChannel('customChannel')
                      .invokeMethod('startNewActivity');
                },
              ),
              FilledButton(
                child: const Text('Open Gleap'),
                onPressed: () async {
                  await Gleap.open();
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
