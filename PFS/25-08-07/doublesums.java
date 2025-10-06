/*
14->19
10->11
99->9999
*/
import java.util.*;
class doublesums{
    public static int doubles(int num, int sum,int doublenum, int count){
        while(num>0){
            int r=num%10;
            if(sum>=9) {
                sum-=(9-r);
                r=9;
            }
            else{
                if(r+sum>9){
                    sum-=(9-r);
                    r=9;
                }
                else {
                    r+=sum;
                    sum=0;
                }
            }
            doublenum+=r*(Math.pow(10,count));
            count++;
            num=num/10;
        }
        while (sum > 0) {
            int add = Math.min(9, sum);
            doublenum += add * (int) Math.pow(10, count);
            sum -= add;
            count++;
        }
        return doublenum;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int num=n;
        int sum=0;
        while(n>0){
            int r=n%10;
            sum+=r;
            n=n/10;
        }
        System.out.println(doubles(num, sum, 0, 0));
    }
}