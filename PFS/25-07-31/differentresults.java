/*
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. 
You may return the answer in any order.

The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.

Example 1:
input =2-1-1
output = [0,2]

Explanation:
((2-1)-1) = 0 
(2-(1-1)) = 2


Example 2:
input =2*3-4*5
output =[-34,-14,-10,-10,10]

Explanation:
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10

case =4
input =2*3+4
output =[10, 14]
Explanation:
(2 * 3) + 4 = 10
2 * (3 + 4) = 14


Constraints:

1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
The integer values in the input expression do not have a leading '-' or '+' denoting the sign.

*/
import java.util.*;
class differentresults{
    public static List<Integer> evaluate(String expression){
        List<Integer> results=new ArrayList<>();
        HashMap<String,List<Integer>> hmap=new HashMap<>();
        
        if(hmap.containsKey(expression)) return hmap.get(expression);
        
        for(int i=0;i<expression.length();i++){
            char c=expression.charAt(i);
            
            if(c=='+' || c=='-' || c=='*'){
                String left=expression.substring(0,i);
                String right=expression.substring(i+1);
                
                List<Integer> leftResults= evaluate(left);
                List<Integer> rightResults=evaluate(right);
                
                for(int l:leftResults){
                    for(int r: rightResults){
                        int res=0;
                        switch(c){
                            case '+': results.add(l+r); break;
                            case '-': results.add(l-r); break;
                            case '*': results.add(l*r);break;
                        }
                    }
                }
            }
        }
        if(results.isEmpty()) results.add(Integer.parseInt(expression));
        hmap.put(expression, results);
        Collections.sort(results);
        return results;
    }
    
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        String expression=sc.next();
        System.out.println(evaluate(expression));
    }
}