package rcpTest;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface IProxyProtocol extends VersionedProtocol {
    public static final long versionID = 1L; 
    int Add(int number1,int number2);
    int construct(int n, int m);
    int product();
    int send();
    
}