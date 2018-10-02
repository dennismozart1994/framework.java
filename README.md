# A Functional multi-task automation Framework

That's a JAVA automation framework that contaisn the common functionalities that an automator would use.

## Getting Started



### Prerequisites

Have Java installed and the variable paths configured properly.
Have Appium server installed and configured to use Mobile automation.
Have Android Studio installed and configured to use Android Mobile Automation.
Have the last Version of Xcode and Xcode Command line tools installed to use iOS Automation.
Have configure the .properties file to use the proper image and header colour during the evidence generation.

### Installing

Follow the steps bellow to setup the project into your machine.
```
1 - Clone the repository on your local machine
2 - Import the project as a Maven Project into one of your favorite IDE's
3 - Create a folder called "Evidences" into the project root
4 - Wait until the dependencies being downloaded and the project finish building and your good to go.
```
The evidence folder will be the one that will receive all pdf files containing the test evidence.

## Functionalities

## Commands for Web Automation

### start();

Launch an instance of a WebDriver according to the value set on the propertie "Browser" of the config file.

Options available:
```
CHROME - Launch the Google Chrome Browser
FIREFOX - Launch the Firefox Browser
NONE - Launch the IE Browser
```

### Click(WebElement);
Click on the Web Element provided into the parameter.

### Click(String);
Click on the link that contains the text provided into the parameter.

### Click(String);
Click on the link that contains the text provided into the parameter.

### InsertDataIntoField(WebElement, String);
Fill a Web Element form field

### Marklement(WebElement);
Check a radio or checkbox value based on a Web Element.

### ComboSelect()
Select a combobox value, theres variations of that command available:

```
ComboSelect(WebElement fiel, Text) - Select based on the visible text for the user
ComboSelect(WebElement, Integer) - Select based on the index of the value to select at the combo.

```



## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
