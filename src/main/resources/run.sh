cd /home/wieczorek_marcin1993
rm -r *

hadoop fs -rm -r *

hadoop fs -mkdir -p project/hadoop/mapreduce/input
hadoop fs -mkdir -p project/hadoop/pig/input

hadoop fs -copyToLocal gs://dataproc-staging-europe-west3-58226320042-kulwuzjk/project/*

hadoop fs -copyFromLocal combined_data_1.txt project/hadoop/mapreduce/input
hadoop fs -copyFromLocal combined_data_2.txt project/hadoop/mapreduce/input
hadoop fs -copyFromLocal combined_data_3.txt project/hadoop/mapreduce/input
hadoop fs -copyFromLocal combined_data_4.txt project/hadoop/mapreduce/input

hadoop fs -copyFromLocal NetflixPrizeData-1.0-SNAPSHOT.jar project/hadoop

hadoop jar NetflixPrizeData-1.0-SNAPSHOT.jar Driver project/hadoop/mapreduce/input project/hadoop/mapreduce/output

hdfs dfs -getmerge project/hadoop/mapreduce/output result.csv

pig -x local script.pig

cat results/*