# Halo-Suit-Test-Server
Halo-Suit-Test-Server is an application designed to simulate the network layer between different Halo-Suit Devices and give developers fine grained control of that network layer.
It currently supports the [Android](https://github.com/SFU-Embedded-Cosplay/Halo-Suit-Android) and [BeagleBone](https://github.com/SFU-Embedded-Cosplay/Halo-Suit-BeagleBone) applications

##Testing Android Device##
In order to use the Test server to test the Android device you must make a number of configuration changes.

1. Launch the test server and click the button labelled **Wait for connection**.
2. Open the Android code in Android studio and navigate the the [AndroidBlue.java](https://github.com/SFU-Embedded-Cosplay/Halo-Suit-Android/blob/master/app/src/main/java/com/haloproject/bluetooth/AndroidBlue.java) file.
3. change the variable **IS_TESTING_WITH_SOCKET** to true.
4. Launch the Android application in the Android phone enulator on your computer. The Top right Bluetooth status indicator should now be yellow instead of red.
 1. **Warning:** The test server does not work if you launch the application on a real phone.
5. Navigate to the Settings tab and click **Start Discovery**.
6. You should now be connected to the mock server and can send messages back and forth.
