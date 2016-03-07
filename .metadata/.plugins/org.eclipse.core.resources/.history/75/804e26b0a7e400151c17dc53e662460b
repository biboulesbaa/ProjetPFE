package tfidf;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
public class MatrixTfidfReducer extends Reducer<Text, Text, Text, Text> {

	private static Text OUT_KEY = new Text();
	private static Text OUT_VALUE = new Text();

	public MatrixTfidfReducer() {
	}

	/**
	 * PRECONDITION: receive a list of <word, ["doc1=n1/N1", "doc2=n2/N2"]>
	 * 
	 * POSTCONDITION: <"word@doc1,  [d/D, n/N, TF-IDF]">
	 * 
	 * @param key
	 *            is the key of the mapper
	 * @param values
	 *            are all the values aggregated during the mapping phase
	 * @param context
	 *            contains the context of the job run
	 */
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

			OUT_KEY.set(key);
			OUT_VALUE.set(values.iterator().next());
			context.write(OUT_KEY, OUT_VALUE);
		}
	}

