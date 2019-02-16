package Java_Course;
// The class that actually performs on a part of a matrix multiply in a
// thread.  Change this to implement Runnable.
class MM implements Runnable {                                         

    // Times for each thread
    private static long[ ] threadTime = new long[4];

    // Number of threads created so far
    private static int threadCount = 0;

    // id of this thread -- a number between 0 and threadCount - 1;
    private int tID;

    // the range of the partitioned dimension of the array 
    private int endP, startP; 

    // each thread has a unique id from 0 to maxThreads-1.
    public MM(int maxThreads, int arraySize) { 
       tID = threadCount++;
       startP = tID*arraySize;
       endP = (tID+1)*arraySize;
    }

    // getter function to return the private field time
    public static long getThreadTime(int t) {
       return threadTime[t];
    }

    void matrixMultiply( ) throws Exception {
       for (int i = startP; i < ThreadRun.N; i++) {
          for (int j = startP; j < ThreadRun.N; j++) {
             for (int k = 0; k < ThreadRun.N; k++) {
            	 ThreadRun.C[i][j] += ThreadRun.A[i][k] * ThreadRun.B[k][j];
             }
          }
       }
    }

   @Override
public void run( ) {
      threadTime[tID] = System.currentTimeMillis();
      try {
    	  matrixMultiply( );
      }
      catch (Exception e) {
    	  e.printStackTrace();
      }
      threadTime[tID] = System.currentTimeMillis() - threadTime[tID];
   }
}
