package rcpTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.ipc.RPC;



import java.io.IOException;
import java.net.InetSocketAddress;

public class MyClient {
	
	
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(
                MyServer.IPAddress, MyServer.PORT);
        Configuration conf = new Configuration();
        conf.addResource(new Path("/usr/local/hadoop/etc/hadoop/core-site.xml"));
        String hadoopCluster = conf.get("fs.defaultFS")  ;
        try {
            // script de creation de la matrice     
        	ProcessBuilder pb = new ProcessBuilder("/bin/sh", "./run_tfidf.sh" , "input2/" , "output/"  );
       		Process p = pb.start(); 
       		System.out.println("wait create" + p.waitFor());
       		// send part-r-00000 de tfidf3 a inputmatrice
       		pb = new ProcessBuilder("/bin/sh", "./run_distcp.sh" 
        			, hadoopCluster+"/user/hadoopuser/output/tfidf3/part-r-00000"
        			, hadoopCluster+"/user/hadoopuser/inputMatrice/");
            p = pb.start(); 
        	System.out.println("wait send" + p.waitFor());
       		//generation
       		pb = new ProcessBuilder("/bin/sh", "./run_generation.sh" 
        			, hadoopCluster+"/user/hadoopuser/inputMatrice/"
        			, "5" );
        	p = pb.start(); 
    		System.out.println("wait generation" + p.waitFor());
       		// multiplication 
       	    pb = new ProcessBuilder("/bin/sh", "./run_product.sh" 
        			, hadoopCluster+"/user/hadoopuser/inputMatrice/"
        			, hadoopCluster+"/user/hadoopuser/outputMatrice/", "5" , "5" , "40" , "M", "A", "MA");
        	p = pb.start(); 
    		System.out.println("wait multi" + p.waitFor());
       	    // script d envois de la matrice 
        	pb = new ProcessBuilder("/bin/sh", "./run_distcp.sh" 
        			, hadoopCluster+"/user/hadoopuser/outputMatrice/part-r-00000"
        			, hadoopCluster+"/server");
        	p = pb.start(); 
    		System.out.println("wait send" + p.waitFor());
    		
    			
            IProxyProtocol proxy = (IProxyProtocol) RPC.waitForProxy(
                    IProxyProtocol.class, IProxyProtocol.versionID, inetSocketAddress,new Configuration());

            
            System.out.println("construct"+ proxy.construct(10, 40) );
            System.out.println("construct"+ proxy.product() );
            System.out.println("construct"+ proxy.send() );
            
            
            //int result = proxy.Add(145, 25);
            //System.out.println("10+25=" + result);

            RPC.stopProxy(proxy); 
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}