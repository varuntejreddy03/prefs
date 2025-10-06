/*
Once upon a time, in the Kingdom of Numeria, every number was born with a unique identity — its digits. But not all numbers lived joyful lives. Legends spoke of a sacred path that led to Eternal Happiness, a state only a few chosen numbers could reach.

The wise oracle of Numeria revealed a prophecy:

"A number is happy if, by squaring and summing its digits again and again, it finds its way to the Kingdom of One — the land of pure peace."

Curious and courageous, numbers began their journey. The rules were simple but strict:

Take each digit of the number.

Square it and add the results.

Use that result as the next number on the path.

Repeat the process.

Some brave numbers like 7 and 19 reached 1 and were celebrated as Happy Numbers, welcomed into the eternal land of joy.

But others like 116 fell into dark loops, wandering endlessly through the Forest of Repetition, never reaching 1 — trapped forever in sadness.

To help numbers find their fate, the Oracle gave a magical tool: a memory book (a set), to keep track of visited lands. If a number visited the same place again, it meant a curse — a loop — and the quest was over.

And thus, began the great quest: To find whether a number is Happy or Not.


Input: n = 19
Output: true
Explanation:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1


Example 2:
Input: n = 2
Output: false
 

Example 2:
Input: n = 2
Output: false
 */
import java.util.*;
class HappyNum{
    public static int squaring(int n){
        int squares=0;
        while(n!=0){
            int digit=n%10;
            squares+=digit*digit;
            n=n/10;
        }
        return squares;
    }
    public static boolean calculate(int n){
        HashSet<Integer> set=new HashSet<>();
        while(true){
            n=squaring(n);
            if(n==1) return true;
            else if(set.contains(n)) return false;
            set.add(n);
        }
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(calculate(n));
    }
}