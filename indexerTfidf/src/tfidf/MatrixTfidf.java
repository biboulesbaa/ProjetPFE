package tfidf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MatrixTfidf extends Configured implements Tool {

	/**
	 * Setup the MR job
	 * 
	 * @param args
	 * @throws Exception
	 */

	public int run(String[] args) throws Exception {

		if (args.length != 2) {
			System.out.println("Usage: tf-idf-4 <tf-idf-3-output> <tf-idf-4-output>");
			System.exit(-1);
		}

		Configuration conf = getConf();
		Job job = new Job(conf, "Tfidf matrix");

		job.setJarByClass(MatrixTfidf.class);
		job.setMapperClass(MatrixTfidfMapper.class);
		job.setReducerClass(MatrixTfidfReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

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
		int res = ToolRunner.run(new Configuration(), new MatrixTfidf(),
				args);
		System.exit(res);
	}
}