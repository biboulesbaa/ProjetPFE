package utilities;

import java.io.*;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;

public class fusionFile {

	        /*public static void fusion (String cheminW , String cheminR ) {
	                try{
	                        Path ptW=new Path( cheminW );
	                        Path ptR=new Path( cheminR);
	                        FileSystem fs = FileSystem.get(new Configuration());
	                        //BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fs.create(pt,true)));
	                        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fs.create(ptW,true)));
	                        
	                        BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(ptR)));
	                        String line;
	                        line=br.readLine();
	                        while (line != null){
	                                bw.write(line);
	                                line=br.readLine();
	                        }
	                        
	                        bw.close();
	                        br.close();
	                }catch(Exception e){
	                        System.out.println("File not found");
	                }
	        }*/
	
	public static void fusion (String cheminW , String cheminR ) {
	
		String srcPath = "/user/hadoopuser/input"; 
		String dstPath = "/user/hadoopuser/fusion"; 
		Configuration conf = new Configuration(); 
		try { 
			FileSystem hdfs = FileSystem.get(conf); 
		    FileUtil.copyMerge(hdfs, new Path(srcPath), hdfs, new Path(dstPath), false, conf, null); 
		    } catch (IOException e){ 
		    	
		}
	}
	        
}
