# Instrumental Testing Guidelines

## Prerequisites

1. **ADB Installed**: Ensure Android Debug Bridge (adb) is installed and added to your system PATH.
2. **Device Connected**: A physical device or emulator must be connected and authorized (check via
   `adb devices`).
3. **App Built**: The application and its test APK should be built.
   *Debug-App*: 
   ```bash
    ./gradlew assembleDebug
    ```

   *Instrumental-App*:
   ```bash
    ./gradlew assembleDebugAndroidTest
    ```
   
4. Then, run below command:
    ```bash
    ./gradlew connectedAndroidTest 
    ```
