# SimpleHttpServer
Simple Http server builded in basic Java libraries  

# ConfigHttpServer
Class with persistent parameters of CustomHttpServer configuration  
You may modify HTTP_HOST and HTTP_PORT as you wish  

# CustomHttpServer
Main class for Simple and Lightweight HTTP Server creation  
Here is a configuring and creating of HTTP server  
Implemented two routers: OMDB and IMDB for different recourses API  

# UtilityHttpServer
Utility class with converters and requesters  
Contain methods for execution of HTTP request to external API  
The same way implemented methods for parsing XML and converting to JSON with using recursion of methods  

# OmdbApiHandler
Class for handling response to OmdbApi path of REST request  
Usage:
```
http://[host]:[port]/omdb/<string of request>
```
or
```
http://[host]:[port]/omdb/?q=<string of request>
```
working similarly
  
# ImdbApiHandler
Class for handling response to ImdbApi path of REST request  
Usage:
```
http://[host]:[port]/imdb/<string of request>
```
or
```
http://[host]:[port]/imdb/?q=<string of request>
```
working similarly
