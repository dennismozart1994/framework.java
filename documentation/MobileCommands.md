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

```
LaunchBrowserApp(CharSequence Browser) - Open the browser passed on the parameter.
LaunchAndInstallApp(String App) - Install and open app based on the path passed on the parameter.
LaunchAndroidLocalApp(String PackageName, String Activity) - Launch Android app already installed based on the parameters.
LaunchIOSLocalApp(String BundleID) - Launch iOS app already installed based on the bundle ID of the app.
```

## Authors

* **Dennis Mozart** - *QA Analyst and Automator* - [dennismozart1994](https://github.com/dennismozart1994)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
