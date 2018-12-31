### Prerequisites

* Have Java installed and the variable paths configured properly.
* Have Appium server installed and configured to use Mobile automation.
* Have Android Studio installed and configured to use Android Mobile Automation.
* Have the last Version of Xcode and Xcode Command line tools installed to use iOS Automation.
* Have configure the .properties file to use the proper image and header colour during the evidence generation.

### Installing

Follow the steps bellow to setup the project into your machine.
```
1 - Clone the repository on your local machine
2 - Import the project as a Maven Project into one of your favorite IDE's
3 - Create a folder called "Evidences" into the project root
4 - Wait until the dependencies being downloaded and the project finish building and your good to go.
5 - If you wanna Run on Android, set the config "Device" as Android device or Android simulator, depending of where you wanna run
6 - If you wanna Run on Android, set the config "Device" as iPhone device or iPhone simulator, depending of where you wanna run.
7 - If you wanna Run on Android, set the config "Device" as iPad device or iPad simulator, depending of where you wanna run.
```

The evidence folder will be the one that will hold all .pdf evidences.

## Functionalities
### Launch Apps
```
LaunchBrowserApp(CharSequence Browser) - Open the browser passed on the parameter.
LaunchAndInstallApp(String App) - Install and open app based on the path passed on the parameter.
LaunchAndroidLocalApp(String PackageName, String Activity) - Launch Android app already installed based on the parameters.
LaunchIOSLocalApp(String BundleID) - Launch iOS app already installed based on the bundle ID of the app.
```
### Mapping elements methods
```
findByUIAutomator(String Attribute, String Value) - Find elements based on Android UI automator attribute and value e.g attribute = text, value = Click here!
findById(String Id) - Find element based on his id (Android)
findByAcessibilityID(String ID) - Find element based on his Accessibility ID (iOS)
findByName(String Name) - Find element based on his tag name
findByXpath(String Xpath) - Find element based on his xPath
findByClassName(String ClassName) - Find element based on his Class Name
```
### Getters
```
GetContent(MobileElement element) - get the text of an element
GetContext() -  return if it's a Native_APP only, Hybrid APP or Web view App
GetOrientation() - Return the orientation of the screen
getDeviceSerialNumber(String device) - get the identifier of the device(id for Android or UUID for iOS)
```
### Hybrid apps commands
```
GetCurrentContexts() - return all contexts if there more than one, e.g 1 native   1 Web
SwitchToContext(String ContextHandle) - Switch to different context into hybrid apps, the Context Handle needs to be the exactly string returned into one of the strings from the function GetCurrentContexts() above
```

### Mobile Gestures
```
TapBack() - Tap on the back button for Android Devices
TapHome() - Tap on the Home button for Android Devices
TapSwitch() - Tap on the square button for Android Devices to see the apps opened and switch between them.
NavigateBack() - Go back, equivalent to the <- button on iOS or browsers.
HideKeyboard() - Hide keyboard for iOS and Android devices

Tap(MobileElement element) -  simple tap, click on element
Tap(Integer x, Integer y) - simple tap, click on coordinates
Tap(MobileElement element, Integer x, Integer y) - simple tap, click on coordinates relative to element

DoubleTap(MobileElement element) - Double click on element

LongPress(MobileElement element) - simple click and hold
LongPress(MobileElement element, Integer Seconds) - click and hold for a couple of seconds

Swipe(MobileElement from, MobileElement to) - Swipe from one element to the other
Swipe(MobileElement from, Integer X, Integer Y) - Swipe from one element to coordinates
Swipe(Integer fromX, Integer fromY, Integer toX, Integer toY) - Swipe from one Coordinate to the other

DragAndDrop(MobileElement Origin, MobileElement Destination) - Simple Drag and Drop from element, to element
DragAndDrop(MobileElement Origin, Integer X, Integer Y) - Drag and Drop element into coordinate

setWhellValue(MobileElement element, String value)

 ScrollUntil(String attribute, String Value) - Scroll until element shows up based on UIAutomator for Android devices only
 ScrollUntil(boolean down, MobileElement element) - Scroll until element shows up, boolean down controls the direction(up or down) works on iOS and Android devices
 scrollUntilElementShowUp(MobileElement element) - Scroll until element shows up based on element id for XCUI Automation (iOS only)
```

## Authors

* **Dennis Mozart** - *QA Analyst and Automator* - [dennismozart1994](https://github.com/dennismozart1994)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
