# Content-Provider-Basic
Content Provider 


Content Provider is defined as the center point of database which provides data to different application which are connected with the database through Url. 
When the client request data from their application, those request will on the form of url and those requested data will be accepted by content provider.

First we need to create separate file which inherits properties and function from ContentProvider()

onCreate()
Insert()
Update()
Delete()
Query()
getType()

To call content provider to retrieve data and shown in our screen, we use
val cv = contentResolver.query(URI, arrayofData, condition, conditionArgs, sortType)




