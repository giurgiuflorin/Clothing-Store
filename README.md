## Name of the project
Online Clothing Store - Backend

## Author
Giurgiu Florin Mircea

### General presentation

This project covers the backend for an online clothing store.

To create the necessary database for this project, please refer to src/files, 
where you will find the following:
- create_online_store_database.sql - this file completely sets up the database, including its architecture and procedures.
- populate-database.sql - this file populates the database with initial data for manipulation.


### Schema of relational logic
<img src="eerdiagram.png">

<hr>

To interact with data in the database, Java code uses the native SQL language,
however, in order to expose as little of the tabular structure as possible, database queries
are executed by calling procedures.
See the example below:
