#!/usr/bin/env bash

# Get the input data
#declare INPUT=$1;
#declare OUTPUT=$2;
#declare TYPE=$3


#Remove the output directories
/usr/local/hadoop/bin/hadoop fs -rmr $2 

# Execute the jobs
printf "\nExecuting Job 1: Word Frequency in Doc\n"
/usr/local/hadoop/bin/hadoop jar tfidf.jar tf-idf-1 $1 $2/tfidf1

printf "\nExecuting Job 2: Word Counts For Docs\n"
/usr/local/hadoop/bin/hadoop jar tfidf.jar tf-idf-2 $2/tfidf1 $2/tfidf2

printf "\nExecuting Job 3: Docs In Corpus and TF-IDF\n"
/usr/local/hadoop/bin/hadoop jar tfidf.jar tf-idf-3 $1 $2/tfidf2 $2/tfidf3 $3


