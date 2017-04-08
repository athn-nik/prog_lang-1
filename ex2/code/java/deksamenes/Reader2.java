//package myReader;
import java.util.Scanner;
import java.io.* ;
import java.lang.Math.* ;


public class Reader2{
    public  int N;
    public  long M;
    public  int i,j,k;
    public  long sum,min,max,median;
    public  double ret;
    public  long [] [] A;
    public Scanner scanner;

    public Reader2(File arg){		
        try{
            scanner = new Scanner(arg);
            N = scanner.nextInt();
            A = new long [N][4];
            A[0][0] = scanner.nextInt()*1000;
            A[0][1] = scanner.nextInt()*1000;
            A[0][2] = scanner.nextInt();
            A[0][3] = scanner.nextInt();
            min = A[0][0];
            max = A[0][0]+A[0][1];
            for(i = 1; i < N; i++){
                A[i][0] = scanner.nextInt()*1000L;
                A[i][1] = scanner.nextInt()*1000;
                A[i][2] = scanner.nextInt();
                A[i][3] = scanner.nextInt();
                if (min > A[i][0]) {
                    min = A[i][0];
                }
                if (max < A[i][0]+A[i][1]) {
                    max = A[i][0]+A[i][1];
                }
            }
            M = scanner.nextLong()*1000L;
        
        }
        catch (Exception e){
        }

    }

    public long[][] getArray(){				
        return A;
    }
    public int getN(){
        return N;
    }
    public long getM(){
        return M;
    }
    public long getMin(){
        return min;
    }
    public long getMax(){
        return max;
    }
    
}
