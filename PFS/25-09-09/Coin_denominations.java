/*
You are given an amount V and a set of coin denominations. Your task is to find the minimum number of coins required to make up the 
amount V using those denominations.

Assume there are infinite coins of each denomination.
If it is not possible to make the amount, return an empty list.
Constraint: Greedy only works when coin system is canonical (like Indian/US currency).

input =93
1 2 5 10 20 50 100 500 2000
output =5
50 20 20 2 1

Explanation
V = 93
Coins = [1, 2, 5, 10, 20, 50, 100, 500, 2000]
Output:
Minimum coins required: 5
Coins used: [50, 20, 20, 2, 1]

We first pick 50, then 20, 20, then 2, and finally 1 → total 5 coins.

case =1
input =121
output =3
[100, 20, 1]

case =2
input =93
output =5
[50, 20, 20, 2, 1]
*/
//Using Greedy Approach
/*import java.util.*;
class Coin_denominations{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int V=sc.nextInt();
        sc.nextLine();
        String denominations=sc.nextLine();
        String[] coins=denominations.split(" ");
        List<Integer> used=new ArrayList<>();
        for(int i=coins.length-1;i>=0;i--){
            int coin=Integer.parseInt(coins[i]);
            while(V>=coin){
                V-=coin;
                used.add(coin);
            }
        }
        if(V>0) System.out.println("-1");
        else{
            System.out.println(used.size());
            System.out.println(used);
        }
    }
}
*/
//Grade: 80
//Using dp approach
import java.util.*;
class Coin_denominations{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int V=sc.nextInt();
        sc.nextLine();
        String denominations=sc.nextLine();
        String[] tokens=denominations.split(" ");
        int[] coins=new int[tokens.length];
        for(int i=0;i<tokens.length;i++){
            coins[i]=Integer.parseInt(tokens[i]);
        }
        Arrays.sort(coins);
        int[] dp=new int[V+1];
        int[] parent=new int[V+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(parent,-1);
        dp[0]=0;
        for(int i=0;i<coins.length;i++){
            for(int j=coins[i];j<=V;j++){
                if(dp[j-coins[i]]!=Integer.MAX_VALUE && dp[j-coins[i]]+1<dp[j]){
                    dp[j]=dp[j-coins[i]]+1;
                    parent[j]=coins[i];
                }
            }
        }
        if(dp[V]==Integer.MAX_VALUE){
            System.out.println("-1");
            return;
        }
        List<Integer> used=new ArrayList<>();
        int amount=V;
        while(amount>0){
            int coin=parent[amount];
            used.add(coin);
            amount-=coin;
        }
        used.sort(Collections.reverseOrder());
        System.out.println(dp[V]);
        System.out.println(used);
    }
}
/*TestCases:
case =1
input =93
1 2 5 10 20 50 100 500 2000
output =5
50 20 20 2 1

case =2
input =37
1 5 10 25
output =4
[25, 10, 1, 1]


case =3
input =6
1 3 4
output =2
[3, 3]

case =4
input =2758
1 2 5 10 20 50 100 500 2000
output =8
[2000, 500, 100, 100, 50, 5, 2, 1] 

case =5
input =7
2 4 6
output =-1
*/