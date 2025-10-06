/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock,
 and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. 
If you cannot achieve any profit, return 0.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 5

Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.


Example 2:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
*/
/*
import java.util.*;
class MaxProfit{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int days=sc.nextInt();
        int[] prices=new int[days];
        for(int i=0;i<days;i++){
            prices[i]=sc.nextInt();
        }
        int max_profit=0,profit=0;
        for(int i=0;i<days;i++){
            for(int j=i;j<days;j++){
                if(prices[i]<prices[j]){
                    profit=prices[j]-prices[i];
                    if(profit>max_profit) max_profit=profit;
                }
            }
        }
        System.out.println(max_profit);
    }
}
*/
import java.util.*;
class MaxProfit{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int days=sc.nextInt();
        int[] prices=new int[days];
        for(int i=0;i<days;i++){
            prices[i]=sc.nextInt();
        }
        int min_price=Integer.MAX_VALUE;
        int max_profit=0;
        for(int i=0;i<days;i++){
            if(prices[i]<min_price) min_price=prices[i];
            int profit=prices[i]-min_price;
            if(profit>max_profit) max_profit=profit;
        }
        System.out.println(max_profit);
    }
}