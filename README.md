# Halo-Suit-Test-Server
Halo-Suit-Test-Server is an application designed to simulate the network layer between different Halo-Suit Devices. The Test Server gives developers control of both sending and receiving messages.
It currently supports the [Android](https://github.com/SFU-Embedded-Cosplay/Halo-Suit-Android) and [BeagleBone](https://github.com/SFU-Embedded-Cosplay/Halo-Suit-BeagleBone) applications.

##Testing Android Device##
In order to use the Test server to test the Android device you must make a number of configuration changes.

1. Launch the Halo-Suit-Test-Server application and click the button labelled **Wait for connection**.
2. Open the Android code in Android studio and navigate to the [AndroidBlue.java](https://github.com/SFU-Embedded-Cosplay/Halo-Suit-Android/blob/master/app/src/main/java/com/haloproject/bluetooth/AndroidBlue.java) file.
3. Change the variable **IS_TESTING_WITH_SOCKET** to true.
4. Launch the Android application in the Android phone enulator on your computer. 
 1. The Top right Bluetooth status indicator should now be **yellow instead of red**.
 2. **Warning:** The test server does not work if you launch the application on a real phone.
5. Navigate to the Settings tab and click **Start Discovery**.
6. You should now be connected to the mock server and can send JSON messages to the phone and view any messages the phone sends to the testing server.

##Testing BeagleBone Device##
When using the mock hardware module in the Halo-Suit-BeagleBones you need to use the Halo-Suit-Test-Server in order to alter the hardware values and read any messages the beaglebone sends in response.  The only change you should have to perform is compiling with make localTest rather than make.

1. Launch the Halo-Suit-Test-Server application and click the button labelled **Wait for connection**.
2. SSH into the BeagleBone and compile the Halo-Suit-BeagleBone application using **make localTest**.
3. Launch the suitcontroller application that is generated using **./suitcontroller**.
4. You should now be connected to the mock server and can send JSON messages from the server to the BeagleBone to alter the hardware values.  
