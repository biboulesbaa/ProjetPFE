#!/usr/bin/env bash

###############################################################################
# This script is used to perform TF-IDF as a sequence of 3 MapReduce jobs.
#
# Usage:
#    ./run_tfidf.sh <input_dir> <output_dir> [conf]
#  
#  where:
#     input_dir  = HDFS Directory with the input files
#     output_dir = HDFS Directory to place the output
#     conf       = Optional hadoop configuration options
#
# This script will create three directories under <output_dir>, namely,
# tfidf1, tfidf2, and tfidf3, to place to output from each MR job.
#
# Examples:
#    ./run_tfidf.sh ~/input ~/output "-conf=myconf.xml"
#    ./run_tfidf.sh ~/input ~/output "-Dmapred.reduce.tasks=2"
#
# Author: Herodotos Herodotou
# Date: November 5, 2010
##############################################################################


# Make sure we have all the arguments
if [ $# -ne 2 ] && [ $# -ne 3 ]; then
   printf "./run_tfidf.sh <input_dir> <output_dir> [conf]\n"
   printf "   input_dir  = HDFS Directory with the input files\n"
   printf "   output_dir = HDFS Directory to place the output\n"
   printf "   conf       = Optional hadoop configuration options\n"
   exit -1
fi

# Get the input data
declare INPUT=$1;
declare OUTPUT=$2;
declare CONFIG=$3;

# Remove the output directories
hadoop fs -rmr $OUTPUT

# Execute the jobs
printf "\nExecuting Job 1: Word Frequency in Doc\n"
hadoop jar tfidf.jar tf-idf-1 $CONFIG $INPUT $OUTPUT/tfidf1

printf "\nExecuting Job 2: Word Counts For Docs\n"
hadoop jar tfidf.jar tf-idf-2 $CONFIG $OUTPUT/tfidf1 $OUTPUT/tfidf2

printf "\nExecuting Job 3: Docs In Corpus and TF-IDF\n"
hadoop jar tfidf.jar tf-idf-3 $CONFIG $INPUT $OUTPUT/tfidf2 $OUTPUT/tfidf3

#printf "\nExecuting Job 3: Docs In Corpus and TF-IDF\n"
#hadoop jar tfidf.jar tf-idf-4 $CONFIG $OUTPUT/tfidf3 $OUTPUT/tfidf4

