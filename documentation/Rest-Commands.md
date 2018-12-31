# A Functional multi-task automation Framework

That's a JAVA automation framework that contaisn the common functionalities that an automator would use.

## Getting Started

### Pre-requisites

* Have Java installed and the variable paths configured properly.
* Have configure the .properties file to use the proper image and header colour during the evidence generation.

### Installing

Follow the steps bellow to setup the project into your machine.
```
1 - Clone the repository on your local machine
2 - Import the project as a Maven Project into one of your favorite IDE's
3 - Create a folder called "Evidences" into the project root
4 - Wait until the dependencies being downloaded and the project finish building and your good to go.
5 - At the moment, for organization purposes, the rest sevice depends of an Excel spreadsheet to organize the JSON requests.
```
The evidence folder will be the one that will hold all .pdf evidences.

## Functionalities

## Commands for automate Rest API calls

### Interactions
```
start() - Set the spreadsheet with the json structure
```

### Build Commands
```
BuildHeaders(RequestSpecification request) - Create headers based on the fields of the spreadsheet
BuildJSON() - Create JSON Object based on the spreadsheet
PreetyJSON(String JSON) - Print preety JSON
printResponse() - PRINT THE ENTIRE RESPONSE
```

### Action Commands
```
GETCommand(String EndPoint, String Path) - GET request
POSTCommand(String EndPoint, String Path) - POST request
```

### Getters
```
Headers getHeaders() - Get all headers of a JSON
printAllHeaders(Headers headers) - Print all headers based on the result of the function above
getHeader(String header) - Get one specific header
getStatusCode() - GET STATUS CODE FROM RESPONS
getStatusMessage() - GET STATUS MESSAGE FROM RESPONS
getResponse() - GET RESPONSE BODY
getJSONfield(String JSONpath) - GET SPECIFIC JSON FIELD OF THE BODY
getXMLfield(String Xpath) - GET SPECIFIC XML FIELD OF THE BODY

```

## Authors

* **Dennis Mozart** - *QA Analyst and Automator* - [dennismozart1994](https://github.com/dennismozart1994)

See also an test [example](https://github.com/dennismozart1994/framework.java/blob/master/src/test/java/testScenarios/RestPOSTTestExample.java) in this project to get the hang of how wor with it.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments
* Hat tip to anyone whose code was used
* Inspiration
* etc
