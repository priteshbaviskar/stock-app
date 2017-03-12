# stock-app
Web app which analyzes historical stock data and returns buy/sell dates which yield maximum profit. The app looks out for 6 months of historical data from today.
Here's an example of the GET call to fetch buy/sell dates for ticker SFLY-
- `http://localhost:8080/stock-webservice/stock-analysis/SFLY/maxyield`
- A standard response would look something like this- 

`{
  "responseMessage": "These dates would yield maximum returns",
	"isSuccess": true,
  "object": {
    "responsepair": {
      "buyDate": "2016-10-26",
      "sellDate": "2017-01-17"
    }
  }
}`

- The app throws an error if the ticker is invalid or if no data is available.
- Eg. `http://localhost:8080/stock-webservice/stock-analysis/GHJKLL/maxyield` would return 

`{
  "responseMessage": "Ticker name is not valid.",
  "isSuccess": false,
  "object": {}
}`

### Steps to deploy
- Use maven to build project. Simply type `mvn clean install` from stock-root folder. 
- The project generates an artifact called stock-webservice.war. Move this WAR file to your local tomcat/webapps directory and you are all set to run the application.
