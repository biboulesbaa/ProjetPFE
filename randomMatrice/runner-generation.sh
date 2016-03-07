hadoop jar build/jar/generation.jar inputMatrice/ 5
echo 'Job Input'
echo '----------'
echo ''
echo ''
echo 'Job Output'
echo '----------'
#cat hdfs://192.168.200.129:9000/user/hadoopuser/output/part-r-00000
hadoop dfs -cat inputMatrice/matriceR
