// (c) Copyright 2009 Cloudera, Inc.
// Hadoop 0.20.1 API Updated by Marcello de Sales (marcello.desales@gmail.com)
package tfidf;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import utilities.filesMGR;
import utilities.terms;

/**
 * WordFrequenceInDoc Creates the index of the words in documents, mapping each
 * of them to their frequency. (Hadoop 0.20.2 API)
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 */
public class WordFrequenceInDoc extends Configured implements Tool {

	/**
	 * Setup the MR job
	 * 
	 * @param args
	 * @throws Exception
	 */
	public int run(String[] args) throws Exception {

		
       
		if (args.length != 2) {
			System.out.println("Usage: tf-idf-1 <doc-input> <tf-idf-1-output>");
			System.exit(-1);
		}
		terms.getTerms(Driver.HADOOP_CLUSTER+"/user/hadoopuser/Terms/terms.txt");
        try {
			filesMGR.list(args[0]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	    
        Configuration conf=new Configuration();
		Job job = new Job(conf, "Word Frequence In Doc");

		job.setJarByClass(WordFrequenceInDoc.class);
		job.setMapperClass(WordFrequenceInDocMapper.class);
		job.setReducerClass(WordFrequenceInDocReducer.class);
		job.setCombinerClass(WordFrequenceInDocReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		return job.waitForCompletion(true) ? 0 : 1;
	}

	/**
	 * Main driver
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new WordFrequenceInDoc(),
				args);
		System.exit(res);
	}
}
