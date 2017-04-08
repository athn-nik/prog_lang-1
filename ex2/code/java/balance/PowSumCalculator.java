public class PowSumCalculator{
    public  long[][] ad_powerset;
    public 	int N;
    public 	long W;
    public 	int lt=-1;

    public PowSumCalculator(int N,long W){
        this.N = N;
        this.W = W;
        this.lt = -1;		
        int i,j;
        long [][] ad_powerset = new long[N+2][3];
        for(i=0;i<2;i++){
          for(j=0;j<3;j++){
            if ((i==1 && j==0) || (i==1 && j==1)){
                ad_powerset[i][j]=1;
            }
            else{
                ad_powerset[i][j]=0;
            }
           }
        }
        for (i = 2; i < N+2; i++){
            
            if (W <= ad_powerset[i-1][1]){
                W = ad_powerset[i-1][0] - W;
                ad_powerset[i-1][2] = -(i-1);
                N = i-1;
                lt++;
                break;
            }
            ad_powerset[i][0] = ad_powerset[i-1][0]*3;
            ad_powerset[i][1] = ad_powerset[i-1][1] + ad_powerset[i][0];
            ad_powerset[i][2] = 0;
        }
        this.ad_powerset = ad_powerset;
        this.N = N;
        this.W = W;
    }

    public long[][] getPowSum(){
        return ad_powerset;
    }

    public int getLt(){
        return lt;
    }
    
    public int getN(){		
        return N;
    }

    public long getW(){
        return W;
    }


}
