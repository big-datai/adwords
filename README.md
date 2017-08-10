# adwords
This is Scala - Akka project to manage Google Adwords bids, reports in a dynamic way


# Installation
## To install you will need to have maven 3.3.9 
1. Download the repo: git clone https://github.com/2dmitrypavlov/adwords.git 
2. cd adwords 
3. Run; mvn clean install 
4. You are done 


# Configurations:  
You will need to create ad.properties file and put in src/main/resources/. All your google adwords tokens should be there, for example:  
 
 -api.adwords.userAgent=deepricer.com:ads:1  
 -api.adwords.developerToken=token  
 -api.adwords.clientId=client-id  
 -api.adwords.clientSecret=client-server  
 -api.adwords.refreshToken=refresh-token  
 -api.adwords.clientCustomerId=client-cutomer-id    
 Cassandra is used for data storage in ddl you have a script that you should run in you Cassandra DB.

# Run:  
To run the app, you can go App.scala, it runs all the actor system, before that chack usage and understand functionallity of what you want to do, all the raw functions are avilble in Utils.com


# Usages:  
To see different usages of methods and modules go to Utils.scala it has a main function where you can run any utility function directly  whihout the actor system.  
Various Actors you can find in adwords/google/ each actor can be triggered from App.scala by changing the actor name in classOf.




# Technology:
## The code is written in scala using:
1. Akka package for asychronious parrallel work.
2. Google Adwords API
3. Cassandra DB and Phantom connector.


