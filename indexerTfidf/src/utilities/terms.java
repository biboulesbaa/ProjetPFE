package utilities;

	import java.io.*;
	import java.util.*;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import org.apache.hadoop.fs.*;
	import org.apache.hadoop.conf.*;
	 
	public class terms{
		    public static HashMap<String, Integer> listTerms;
	        public static void getTerms(String path) {
	                try{
	                        //Path pt=new Path("hdfs://npvm11.np.wc1.yellowpages.com:9000/user/john/abc.txt");
	                	    Path pt=new Path(path);
	                	    int indice= 0 ; 
	                        FileSystem fs = FileSystem.get(new Configuration());
	                        
	                        BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
	                        String line;
	                        Pattern pattern = Pattern.compile("\\w+");
	                        line=br.readLine();
	                        listTerms = new HashMap<String, Integer>(); 
	                        while (line != null){
                                System.out.println(line);
    	                    	Matcher m = pattern.matcher(line.toString());
    	                		while (m.find()) {
    	                			String matchedKey = m.group().toLowerCase();
    	                			listTerms.put(matchedKey, indice);
    	                			indice++ ; 
    	                		}
    	                		line=br.readLine(); 
                            }
	                    			
	                			
	                }catch(Exception e){
	                }
	        }
	}

