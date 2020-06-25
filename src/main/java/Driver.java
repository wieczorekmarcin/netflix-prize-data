import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {
	public static  void main(String[] args) throws Exception {
		// instantiate a configuration
		Configuration configuration = new Configuration();
		configuration.set("mapreduce.output.textoutputformat.separator", ",");

		// instantiate a job
		Job job = Job.getInstance(configuration, "Top Rated Netflix Movies");

		// set job parameters
		job.setJarByClass(TopRatedMovies.class);
		job.setMapperClass(TopRatedMovies.MovieMapper.class);
		job.setReducerClass(TopRatedMovies.MovieRecuder.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);

		// set io paths
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));


		System.exit(job.waitForCompletion(true)? 0 : 1);
	}
}