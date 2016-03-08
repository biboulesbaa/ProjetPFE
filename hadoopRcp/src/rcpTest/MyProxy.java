package rcpTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;


public class MyProxy implements IProxyProtocol {
    public int Add(int number1,int number2) {
        System.out.println("addition");
        int result = number1+number2;
        return result;
    }

    public long getProtocolVersion(String protocol, long clientVersion)
            throws IOException {
        System.out.println("MyProxy.ProtocolVersion=" + versionID);
        return versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash) throws IOException {
        return new ProtocolSignature(versionID, null);
    }

	@Override
	public int construct(int n, int m) {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder("/bin/sh", "./run_tfidf.sh" , "inputserver/" , "outputserver/"  );
   		Process p;
		try {
			p = pb.start();
			return p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			return 1;
		}	
 
	}

	@Override
	public int product() {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
        conf.addResource(new Path("/usr/local/hadoop/etc/hadoop/core-site.xml"));
        String hadoopCluster = conf.get("fs.defaultFS")  ;
        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "./run_product.sh" 
    			, hadoopCluster+"/user/hadoopuser/inputMatrice/"
    			, hadoopCluster+"/user/hadoopuser/outputMatrice/", "5" , "40" , "5" , "MA", "B", "MAB");
        Process p;
		try {
			p = pb.start();
			System.out.println("wait multi" + p.waitFor());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return 0;
	}

	@Override
	public int send() {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
        conf.addResource(new Path("/usr/local/hadoop/etc/hadoop/core-site.xml"));
        String hadoopCluster = conf.get("fs.defaultFS")  ;
        
        try {
        	ProcessBuilder pb = new ProcessBuilder("/bin/sh", "./run_distcp.sh" 
        			, hadoopCluster+"/user/hadoopuser/outputMatrice/part-r-00000"
        			, hadoopCluster+"/client");
        	Process p = pb.start();
			System.out.println("wait send" + p.waitFor());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return 0;
	}

}