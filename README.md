# Halo-Suit-Test-Server
[![Build Status](https://travis-ci.org/SFU-Embedded-Cosplay/Halo-Suit-Test-Server.svg?branch=master)](https://travis-ci.org/SFU-Embedded-Cosplay/Halo-Suit-Test-Server)

Halo-Suit-Test-Server is an application designed to simulate the network layer between different Halo-Suit Devices. The Test Server gives developers control of both sending and receiving messages.
It currently supports the [Android](https://github.com/SFU-Embedded-Cosplay/Halo-Suit-Android) and [BeagleBone](https://github.com/SFU-Embedded-Cosplay/Halo-Suit-BeagleBone) applications.

## Working with Maven
The most generic way to explain how to work with maven is by using the command line.  This will first require installing Java 8, Maven 3.*. and optionally installing git, along with configuring their paths in the command line.

First download the repository by either downloading the zip file or cloning the repository using (cloning recommended)
```bash
git clone https://github.com/SFU-Embedded-Cosplay/Halo-Suit-Test-Server
```

Install the library dependencies
```bash
mvn install
```

Create a JAR file 
```bash
mvn deploy
```

Execute the program
```bash
mvn exec:java -D exec.mainClass=main.java.halosuit.Main
```

## Testing Android Device
In order to use the Test server to test the Android device you must make a number of configuration changes.

1. Launch the Halo-Suit-Test-Server application and click the button labelled **Wait for connection**.
2. Launch the Android application in the Android phone enulator on your computer. 
 1. A popup box should appear indicating that you are now in testing mode.
 2. The Top right Bluetooth status indicator should now be **yellow instead of red**.
 3. **Warning:** The test server does not work if you launch the application on a real phone.
3. Navigate to the Settings tab and click **Start Discovery**.
4. You should now be connected to the mock server and can send JSON messages to the phone and view any messages the phone sends to the testing server.

## Testing BeagleBone Device
When using the mock hardware module in the Halo-Suit-BeagleBones you need to use the Halo-Suit-Test-Server in order to alter the hardware values and read any messages the beaglebone sends in response.  The only change you should have to perform is compiling with make localTest rather than make.

1. Launch the Halo-Suit-Test-Server application and click the button labelled **Wait for connection**.
2. Connect the BeagleBone to your computing using the **ethernet over usb connector**.
 1. If you connect the beaglebone using another method, such as an ethernet cable, the connection between the Test Server and BeagleBone may not work and you will likely have to change the IP address in the mockHardware.c file.
3. SSH into the BeagleBone using **ssh root@192.168.7.2**
4. Compile the Halo-Suit-BeagleBone application using **make localTest**.
5. Launch the suitcontroller application that is generated using **./suitcontrollertest**.
6. You should now be connected to the mock server and can send JSON messages from the server to the BeagleBone to alter the hardware values.  
