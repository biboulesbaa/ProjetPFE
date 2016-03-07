package rcpTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;


public class MyServer {
    public static int PORT = 5432;
    public static String IPAddress = "192.168.200.133";

    public static void main(String[] args) throws Exception {

        RPC.Server server = new RPC.Builder(new Configuration()).setProtocol(rcpTest.IProxyProtocol.class)
                .setInstance(new rcpTest.MyProxy()).setBindAddress(IPAddress).setPort(PORT)
                .setNumHandlers(5).build();
        server.start();
    }
}