import java.lang.*;

public class BalSolver{
    public  long[][] ad_powerset;
    public 	int N;
    public 	long W;
    public 	int rt = -1;
    public 	int lt;	
    public 	int flag = 0;
    public BalSolver(int N,long W,int lt,long[][] ad_powerset){
        
        this.W = W;
        this.rt = -1;		
        this.lt = lt;
        this.flag = 0;
        this.ad_powerset = ad_powerset;
        int i;
    
        for(i = N-1; i >= 0; i--){
            if (W == 0L){
                flag = 1;
                break;
            }
            else if (i == 0){
                flag = 0;
                break;
            }
            if (Math.abs(W-ad_powerset[i][0]) <= ad_powerset[i-1][1]){
                W = W - ad_powerset[i][0];
                ad_powerset[i][2] = i;
                rt++;
            }
            else if (Math.abs(W+ad_powerset[i][0]) <= ad_powerset[i-1][1]){
                W = W + ad_powerset[i][0];
                ad_powerset[i][2] = -i;
                lt++;
            }
        }
        this.ad_powerset = ad_powerset;
        this.W = W;
        this.lt = lt;
        this.rt = rt;
        this.flag = flag;
    }

    public long[][] getPowSum(){
        return ad_powerset;
    }

    public int getRt(){
        return rt;
    }
    public int getFlag(){
        return flag;
    }

    public int getLt(){
        return lt;
    }

    public long getW(){
        return W;
    }


}
