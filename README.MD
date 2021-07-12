# Synalogik Technical Test #

An API to read the contents of a plain text file and enable the display of the
total number of words, the average word length, the most frequently occurring word length and a
list of the number of words of each length.

I have put together a basic swagger endpoint to check the two files which were provided within the test.
The first is the example line given in the test, and the second is the bible.

Once the application is started you can hit the following link to check the swagger implementation.

[SwaggerPage](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

There are two endpoints
* [Bible](http://localhost:8080/bible)
  -- Though it would be near impossible for me to validate that the return of the bible's string is correct I have written a lot of tests around the outside of the application to check for what I would consider standard use cases.
* [ExampleFile](http://localhost:8080/exampleFile)
  -- Text file provided within the test document
  
My application only handles resources stored within the actual project for the moment,
Would probably allow for a rest call to pass through a text file or string just so this application isnt entirely stand
alone.

The regex by far took the longest time to suss out due to the nature of things such as:
* dates, eg 24/12/1990
* decimal numbers eg 1.654
* formatted numbers 1,000,000

The way I went about solving that part of the solution is by stripping all non end of line commas and full stops there they would not be considered "words". before running a split to remove any of the other characters.

The rest of the application uses a HashMap as it provided the methods required to store key value pairs used to generate the string response.

As for efficiency there is a test within the SynalogikServiceTest to check how long the entire bible takes to read out. along with a stack overflow link to efficient reading of text files.

Also apologies if there are any non used dependencies within the POM the project structure is a basic template I have created for writing up technical tests 



