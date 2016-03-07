package matriceUtil;

public class generation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			marticeHDFS.write(args[0]+"/matriceR", Integer.parseInt(args[1]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
