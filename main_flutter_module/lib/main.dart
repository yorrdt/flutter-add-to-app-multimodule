import 'package:first_flutter_module/main.dart' as first_module;
import 'package:second_flutter_module/main.dart' as second_module;

void main() {}

@pragma('vm:entry-point')
void startFirstModule() {
  first_module.main();
}

@pragma('vm:entry-point')
void startSecondModule() {
  second_module.main();
}
