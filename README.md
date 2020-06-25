# Netflix Prize Data

## General description of the project
The aim of the project is the practical use of basic data processing platforms used in
Big Data environments.

As part of each project, we will process two related data sets.

Each project will consist of two parts:
* In the first part, using MapReduce processing in the classic (Java) or Hadoop variants
  Streaming, we will process one of the data sets by filtering and aggregating it.
  
* In the second part, using the Pig or Hive platform, we will process the result from the first part and
  the second of the data sets by combining this data, further aggregation, sorting and filtering
  final results.
  
Graphically, the project can be presented as follows:
 
 ![schema](/../master/src/main/resources/schema.png?raw=true "schema")

## Technical aspects
Technically, the project will consist of:

1. The MapReduce program in the classic (Java) or Hadoop Streaming (2) variants, which operating on
   the first data set (1) will generate the result (3) by placing it in the HDFS file system
   
2. Script Pig or Hive (5), which, acting on the result of MapReduce (3) and the second data set (4),
   will generate the final processing result (6) by placing it in the HDFS file format
   JSON
   
3. The script will be run from the command line
    * Prepare the HDFS file system by removing directories from previous runs and creating
    needed directories (we use relative path).
    * Copied from the current directory two source data sets (1) and (4) to the cluster HDFS file system
    * Launched MapReduce (2)
    * Run the Pig / Hive program (5)
    * Downloaded the finished processing result (6) to the local file system, to the current directory and
    displayed its contents

## Project stages

1. MapReduce program
    * Mapper
    * Reducer
    * Using a structured output file
2. Pig / Hive program
    * implementation of processing
    * Defining and using the schemas of both data sets during processing
    * Placing a set of commands in a script that can be run from the command line
    * Generating the resulting data set in JSON format
3. The script that will prepare the data for processing and start processing
    * Preparation of the HDFS file system 
    * Preparation of data sets
    * Start of MapReduce
    * Start of the Pig / Hive program
    * Downloading and displaying the processing result
    
## Source data
The origin of the data is https://www.kaggle.com/netflix-inc/netflix-prize-data

Two data sets: 
* netflix-prize-data.zip - information on movie ratings given by individual
users (1)
* movie_titles.csv - information about movies (4)

### MapReduce program (2)
Operating on the netflix-prize-data set, you must set the number of votes and the sum of the scores for each movie
The resulting set (3) should contain attributes:
* movie ID and
* number of votes cast for the film
* the sum of the ratings given per film

### Program Pig / Hive (5)
Acting on the result of the MapReduce task and the movie_titles.csv dataset, three should be determined best
rated movies (with the highest average rating) for each year of film production, including only those
movies with more than 1,000 votes.

### The final result
The final result (6) should contain the following attributes:
* movie_title - movie title
* production_year - year of film production
* average_credit - average rating of the film

A special feature of the set: use of complex value in MapReduce
    
## How to run
1. Utworzenie klastra
2. Umieść w zasobniku dane źródłowe oraz skrypt uruchamiający.
3. Uruchomienie `run.sh` z poziomu klastra.