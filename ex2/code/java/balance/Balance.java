public class Balance{
    public static int N;
    public static long W;	
    public static long [][] ad_powerset = new long [N+2][3] ;
    public static int lt,rt;
    public static int flag;	
    
    public static void main(String[] args){
        N = Integer.parseInt(args[0]);
        W = Long.parseLong(args[1]);
        int i;
        PowSumCalculator PowSum = new PowSumCalculator(N,W);
        ad_powerset = PowSum.getPowSum();
        lt = PowSum.getLt();
        N = PowSum.getN();
        W = PowSum.getW();

        BalSolver balSolver = new BalSolver(N,W,lt,ad_powerset);
        ad_powerset = balSolver.getPowSum();
        lt = balSolver.getLt();
        rt = balSolver.getRt();
        W = balSolver.getW();
        flag = balSolver.getFlag();

        if (flag == 1){
            System.out.print("[");
            for(i = 0; i < N+1; i ++){
                if (ad_powerset[i][2] > 0L){ 
                    System.out.print(ad_powerset[i][2]);
                    if (rt > 0){
                        System.out.print(",");
                        rt--;
                    }
                }
            }				
            System.out.print("] [");
            for(i = 0; i < N+1; i ++){
                if (ad_powerset[i][2] < 0L){
                    System.out.print(-ad_powerset[i][2]);
                    if (lt > 0){
                        System.out.print(",");
                        lt--;
                    }
                }
            }				
            System.out.println("]");
        }
        else System.out.println("[] []");
    }
}
