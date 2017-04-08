import java.util.Scanner;
import java.io.* ;
import java.lang.Math.* ;

public class Deksamenes {

    public static int N;
    public static long M;
    public static long [][] A = new long [N][4] ;
    public static long sum, min, max, median;
    public static int i, j, k;
    public static Reader2 reader ;	

    public static void main(String[] args){
        try{			
            reader = new Reader2(new File(args[0]));
            N = reader.getN();
            min = reader.getMin();
            max = reader.getMax();			
            M = reader.getM();
            A = reader.getArray();
            
            double result;
            sum = 0L;

            for(i = 0; i < N; i++){
                if (((A[i][0]+A[i][1]) > A[i][0]) && ((A[i][0]+A[i][1]) < (A[i][0]+A[i][1]))) {
                        sum = sum+(A[i][0]+A[i][1]-A[i][0])*A[i][2]*A[i][3];
                }
                else if  (((A[i][0]+A[i][1]) > A[i][0]) && !((A[i][0]+A[i][1]) < (A[i][0]+A[i][1]))){
                        sum = sum+A[i][1]*A[i][2]*A[i][3];
                }
            }
        
            
        if (sum <= M) {
                System.out.println("Overflow");
                    }
        else {
                median = (max+min)/2;
                for(;;){
                    if (min==max) {
                        break;
                    }
                    sum = 0L;
                    
                    for(i = 0; i < N; i++){
                        if ((median > A[i][0]) && (median < (A[i][0]+A[i][1]))) {				 
                                sum += (median-A[i][0]) * A[i][2] * A[i][3];
                        }
                        else if ((median > A[i][0]) && !(median < (A[i][0]+A[i][1]))){
                                sum += A[i][1]*A[i][2]*A[i][3];
                        }
                    }
                    if (sum >= M) {
                        max = median;
                    }
                    else {
                        min = median+1;
                    }
                    median = (max+min)/2;
                }
                
                result = (double)median;
                result = result/10;
                result = Math.round(result);
                result = result/100;
                System.out.println(result);
        
            }
        }
        catch (Exception e){
        }
    }
}
