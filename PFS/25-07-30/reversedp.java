/*
Once upon a time in the Land of Alphabeta, a group of letters stood together, forming a string. They lived happily in a line — side by side — until one day, they heard about a magical mirror that showed them in reverse.

Curious, they wanted to see how they looked backward. But there was a catch.

To reverse their order, they had to play a very special game — taught by an old wise wizard called Recursion.

The wizard said:

“You cannot all jump at once. That would be chaos!
Instead, the first letter must step aside and let the others go ahead.
When the rest finish reversing, you’ll quietly go to the end.”

The first letter agreed and stepped aside.

The second letter heard the same instructions — “Let the others go ahead. You’ll join at the end.”

One by one, each letter stepped aside, letting the rest go ahead, until finally, only one letter remained — the last one.

The wizard told that last letter:

“Now that you're alone, you can begin the reversed line.”

So the last letter stood firm.

Then, one by one, the others who had stepped aside earlier — from second-last to first — joined the line from behind, patiently taking their new places in reverse.

input =ABCD
output =DCBA

Explanation:
A → B → C → D
Became:  D → C → B → A
The line was reversed, and the letters saw their reflection with wonder


*/
import java.util.*;
class reversedp{
    public static String reverse(String str){
        if(str.isEmpty()){
            return "";
        }
        String rest=str.substring(1);
        return reverse(rest)+str.charAt(0);
        /*
        char[] revstr=str.toCharArray();
        char temp;
        for(int i=0;i<str.length();i++){
            temp=revstr[i];
            revstr[i]=revstr[str.length()-1];
            revstr[str.length()-1]=temp;
        }
        return String.valueOf(revstr);
        */
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        System.out.println(reverse(str));
    }
}