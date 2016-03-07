package rcpTest;

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
		ProcessBuilder pb = new ProcessBuilder("/bin/sh", "./run_tfidf.sh" , "input2/" , "output/"  );
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
		return 0;
	}

	@Override
	public int send() {
		// TODO Auto-generated method stub
		return 0;
	}

}