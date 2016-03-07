package matriceUtil;

public class matrix {
	 private final int M;             // number of rows
	    private final int N;             // number of columns
	    public double[][] data;   // M-by-N array

	    // create M-by-N matrix of 0's
	    public matrix(int M, int N) {
	        this.M = M;
	        this.N = N;
	        data = new double[M][N];
	    }
	public static matrix random(int M, int N) {
        matrix A = new matrix(M, N);
        double tmp; 
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
            	tmp = Math.random();
            	tmp=(double)((int)(tmp*1000))/1000;
                A.data[i][j] = tmp;
            }
        }
        return A;
    }
}
