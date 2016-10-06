#OODESIGN SOLUTION

This is a solution for project requirements

For resolve these design requiments we use java features like inheritance, generics and reflections, io.
For each new entity a new csv file is created for example: We create 2 entities Student and School and its managers then
we will have 2 csv files student.csv and school.csv.

UML
https://drive.google.com/file/d/0B_hGB-SOXwDgb2I0ZVdxajlpSFE/view


Classes
-------
GenericManager
--------------
Generic interface for access to database with commons methods for all entities

GenericManagerImpl
------------------
Generic implementation for access to database with commons methods for all entities

StudentManager
--------------
Generic interface for access to Student entity and define specific methods

SchoolManager
-------------
Generic interface for access to Student entity and create specific methods

FileManager
-----------
This interface define commons methods for access to database file(text, csv, excel)

CSVManager
----------
Implementation for database file in csv format

BeanUtils
---------
Utility for read/write a object through reflections.

StudentManagerTest
------------------
A test using assetions for StudentManager for create, update, delete, list and search students.
Run
java com.design.test.StudentManagerTest -ea


SchoolManagerTest
-----------------
A test using assetions for SchoolManager for create schools.
Run
java com.design.test.SchoolManagerTest -ea

EntitySolution
--------------
Command line search

For return all students sorted by date
java EntitySolution student

For return all schools sorted by date
java EntitySolution school

For return students gender=Female
java EntitySolution school gender=Female

For return students gender=Female and type=University
java EntitySolution student gender=Male type=University

For return students name="Louisa Hedgpeth"
java EntitySolution name="Louisa Hedgpeth"

The search by name is a search exactly.


To Do
-----
- We need enhance the search for a approximated search similar to sql's "like".
- The concurrency access was not resolved, we have a incomplete solution therefore is not in this release.
- Maybe we could implement basic sql commands for access to database file.
