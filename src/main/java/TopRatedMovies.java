import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopRatedMovies {
	public static class MovieMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
		IntWritable movieId = new IntWritable();
		IntWritable vote = new IntWritable();
		boolean isMovieIdChanged = false;

		@Override
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			Pattern pattern = Pattern.compile("^[0-9]+:$");
			Matcher matcher = pattern.matcher(value.toString());

			if (matcher.find()) {
				String movieIdWithoutLastChar = value.toString().substring(0, value.toString().length() - 1);
				movieId.set(Integer.valueOf(movieIdWithoutLastChar));
				isMovieIdChanged = true;
			} else {
				String[] parts = value.toString().split(",");
				vote.set(Integer.valueOf(parts[Constants.RATE]));
				isMovieIdChanged = false;
			}

			if (!isMovieIdChanged) {
				context.write(movieId, vote);
			}
		}
	}

	public static class MovieRecuder extends Reducer<IntWritable, IntWritable, IntWritable, Text> {
		Text valueOut = new Text();

		@Override
		public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {

			int voteSum = 0;
			int rateSum = 0;

			for (IntWritable value : values) {
				voteSum ++;
				int rate = value.get();
				rateSum += rate;
			}

			valueOut.set(voteSum + "," + rateSum);
			context.write(key, valueOut);

		}
	}
}