/*
Given an array a, we have to find the minimum product possible with the subset 
of elements present in the array. The minimum product can be a single element also.

Examples: 

Input : a[] = { -1, -1, -2, 4, 3 }
Output : -24
Explanation : Minimum product will be ( -2 * -1 * -1 * 4 * 3 ) = -24

input format:
input =5
-1 -1 -2 4 3
output = -24
                                                                                                               
Input : a[] = { -1, 0 }
Output : -1
Explanation : -1(single element) is minimum product possible
 
Input : a[] = { 0, 0, 0 }
Output : 0

*/
import java.util.*;
class MinmProduct{
    public static int MinProduct(int[] arr, int n){
        Arrays.sort(arr);
        int zero=0, neg=0;
        int maxNeg=Integer.MIN_VALUE;
        long product=1;
        for(int num:arr){
            if(num==0){
                zero++;
                continue;
            }
            if(num<0){
                neg++;
                maxNeg=Math.max(maxNeg,num);
            }
            product*=num;
        }
        if(zero==n) return 0;
        if(neg==0){
            if(zero>0) return 0;
            return Arrays.stream(arr).min().getAsInt();
        }
        if(neg%2==1) return (int)product;
        product/=maxNeg;
        return (int)product;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println(MinProduct(arr,n));
    }
}