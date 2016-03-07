package utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.fs.Path;


import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;

public class filesMGR {
    public static Map<String , Integer> DocIndice = new HashMap<String , Integer>() ; 
	public static void list(String path) throws IOException {
		 try{
	            FileSystem fs = FileSystem.get(new Configuration());
	            FileStatus[] status = fs.listStatus(new Path(path));  

	            for (int i=0;i<status.length;i++){
	            	DocIndice.put(status[i].getPath().getName() , i ) ; 
	            }
	        }catch(Exception e){
	            System.out.println("File not found");
	        }
	    
	}
}
