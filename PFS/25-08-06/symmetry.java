/*
In the mystical land of Numeria, where digits had life and purpose, there stood an ancient Mirror Temple. This temple had a powerful rule:

“Only numbers that look the same after being flipped in a mirror are worthy to enter the Hall of Echoes.”

But the Mirror Temple had one catch.

You couldn't just build the numbers forwards.
You had to build them from the inside out — like a recursive spell, expanding outwards symmetrically, while obeying the rules of the sacred Strobogrammatic Pairs:

'0' ↔ '0'

'1' ↔ '1'

'6' ↔ '9'

'8' ↔ '8'

'9' ↔ '6'

case =1
input =689
output =true

case =2
input =1691
output =true

case =3
input =1881
output =true
*/
import java.util.*;
class symmetry{
    public static boolean isSymmetry(String num, HashMap<Character, Character> hmap){
        if(num.length()<=1) return true;
        else{
            Character left=num.charAt(0);
            Character right=num.charAt(num.length()-1);
            if(hmap.containsKey(left)){
                if(!hmap.get(left).equals(right)) return false;
            }
            else return false;
        }
        return isSymmetry(num.substring(1,num.length()-1),hmap);
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        HashMap<Character, Character> hmap=new HashMap<>();
        hmap.put('0','0');
        hmap.put('1','1');
        hmap.put('6','9');
        hmap.put('8','8');
        hmap.put('9','6');
        System.out.println(isSymmetry(str,hmap));
    }
}