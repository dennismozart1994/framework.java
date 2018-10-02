# A Functional multi-task automation Framework

That's a JAVA automation framework that contaisn the common functionalities that an automator would use.

## Getting Started



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
```
The evidence folder will be the one that will hold all .pdf evidences.

## Functionalities

## Commands for Web Automation

### Interactions
```
start() - Launch an instance of a WebDriver according to the value set on the propertie "Browser" of the config file.

Options available:

CHROME - Launch the Google Chrome Browser
FIREFOX - Launch the Firefox Browser
NONE - Launch the IE Browser
```

```
Click(WebElement element) - Click at the WebElement provided;
Click(String Link) - Click on the link that contains the text provided into the parameter.
```

``` 
InsertDataIntoField(WebElement field, String Text) - Fill a Web Element form field
```


``` 
Marklement(WebElement field) -  Check a radio or checkbox value based on a Web Element. 
```

``` 
ComboSelect(parameters) - Select a combobox value, theres variations of that command available:

Options available for the command:

ComboSelect(WebElement field, String Text) - Select based on the visible text for the user
ComboSelect(WebElement field, Integer Index) - Select based on the index of the value to select at the combo.
ComboSelectByHtmlValue(WebElement field, String Value) - Select based on the value html property of the combo.

```

### Getters
```
GetFieldValue(WebElement field) - Get the content of a form field
```

```
GetText(WebElement Field) - Get text of an WebElement
```

```
IsCheck(WebElement field) - returns true or false if the WebElement is checked
```

```
getSelectvalue(WebElement Field) - Get value selected of a combobox
```

```
getAmountofSelections(WebElement Field) - Get ammount of selections made on a Web List
```

### JavaScript Commands
```
ExecuteJS(String cmd, Object... param) - Execute any JS command based on the command and argumments passed;
```

```
AcceptJSWindow() - Accept JS window alert message
```

```
DismissJSWindow() - Denied JS window alert message
```

```
SendKeysJSWindow(String content) - Send keys into an JS window with a text field
```

```
GetJSWindowMessage() - Get message of an JS window pop-up;
```

### Wait Commands
```
SimpleWait(Integer miliseconds) - Simple tread Sleep on the system.
```

```
WaitUntilElementShowsUp(By field) - Wait until Web Element shows up with an 30 seconds timeout based on the locator provided.
```

## Authors

* **Dennis Mozart** - *QA Analyst and Automator* - [dennismozart1994](https://github.com/dennismozart1994)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments
* Hat tip to anyone whose code was used
* Inspiration
* etc
