package matriceUtil;
import java.io.*;
import java.util.Random;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
//import org.ejml.data.DenseMatrix64F;
//import org.ejml.ops.RandomMatrices;

public class marticeHDFS {
		    public static void Write2 (String path, int k) {
		    	double tmp = Math.random();
            	tmp=(double)((int)(tmp*1000))/1000;
		    	//DenseMatrix64F M = RandomMatrices.createOrthogonal(k,k,new Random());
		    	try{
                    Path pt=new Path(path);
                    FileSystem fs = FileSystem.get(new Configuration());
                    BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fs.create(pt,true)));         
                    String line;
                    for ( int  i = 0 ; i< k ;i++){
                    	for ( int  j = 0 ; j< k ;j++){
                  //  		line= "B,"+ i +"," + j + "," + M.get(i,j) +"\n";
	                //        br.write(line);    
                        }	
                    }
                    
                    br.close();
		    	}catch(Exception e){
		    		System.out.println("File not found");
		    	}
		    }
	        public static void write (String path, int k) throws Exception{
	                try{
	                        Path pt=new Path(path);
	                        FileSystem fs = FileSystem.get(new Configuration());
	                        BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fs.create(pt,true)));
	                        matrix M = matrix.random(k, k) ;         
	                        String line;
	                        for ( int  i = 0 ; i< k ;i++){
	                        	for ( int  j = 0 ; j< k ;j++){
	                        		line= "M,"+ i +"," + j + "," + M.data[i][j] +"\n";
	    	                        br.write(line);    	
		                        }	
	                        }                      
	                        br.close();
	                        

	                        pt=new Path(path+"Inverse");
	                        fs = FileSystem.get(new Configuration());
	                        br=new BufferedWriter(new OutputStreamWriter(fs.create(pt,true)));
	                        
	                        matrix MI=new matrix(k, k);
	                        MI.data=matrix.invert(M.data);
	                        for ( int  i = 0 ; i< k ;i++){
	                        	for ( int  j = 0 ; j< k ;j++){
	                        		line= "MI,"+ i +"," + j + "," + MI.data[i][j] +"\n";
	    	                        br.write(line);    	
		                        }	
	                        }
	                        br.close();
	                        
	                }catch(Exception e){
	                        System.out.println("File not found");
	                }
	        }
	
}
