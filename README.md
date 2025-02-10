# OpenID Connect Demo with Google's OAuth 2.0 APIs 

This is the demo project of how to authenticate REST Service build with Spring Security against Google OpenId Connect Provider. Application is build with Java 8.

## Prerequisites
Follow rules from [Google Developers](https://developers.google.com/identity/protocols/OpenIDConnect) 
to configure OAuth 2.0 credentials in Google API console

**client_id** and **client_secret** parameters needs to be explicitly set in _application.properties_ file or passed via command line (see _Run_ section below)

## Build

```sh
mvn clean install
```

## Run

```sh
java -jar target/openid-connect-demo.jar --client_id=<your_client_id> --client_secret=<your_client_secret>
```

## Test

Navigate on http://localhost:8080/ and click on any API link
Login with your credentials in Google and explore calculation API.

Calculation API build with [Exp4j](https://lallafa.objecthunter.net/exp4j/) calculation library
It's accept calculation string via URL parameter, for example expected return for URL below will be *2*

[http://localhost:8080/calc?exp=(2+2)*2/4](http://localhost:8080/calc?exp=(2%2B2)*2/4)

DEMO USE ONLY !!! No validation and/or cleaning done on input parameters.

## Only If you are wondering - What happened under the hood.

Diagram below I found in this [blog](https://www.citrix.com/blogs/2015/09/11/openid-connectoauth-2-0-integration-with-xenapp-through-unified-gateway/) shows the interaction between 3 parties involved into the authentication process - Browser, Web Service and Google.

![OpenID Connect Sequence Diagram](https://www.citrix.com/blogs/wp-content/uploads/2015/09/220.png)

