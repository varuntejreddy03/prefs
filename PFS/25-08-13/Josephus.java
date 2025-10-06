/*
During the annual school fest, a unique selection process is used to choose a student representative. 
All students stand in a circle and are assigned consecutive roll numbers from 1 to n.

Starting from the student with roll number 1, every k-th student in the circle is asked to step out 
of the selection process. 
The counting continues around the circle (wrapping around to the beginning as needed), 
and each time a student leaves, the next count resumes from the immediate next student in the circle. 
This elimination continues until only one student remains.

Your task is to determine the roll number of the student who will be the last standing after 
all rounds of elimination.

Input Format:
-------------
Line 1: An integer n — the total number of students (with roll numbers from 1 to n).
Line 2: An integer k — every k-th student is eliminated in each round.

Output Format:
--------------
Line 1: A single integer indicating the roll number of the last remaining student.

Constraints:
------------
-> 1 ≤ n ≤ 500
-> 1 ≤ k ≤ n

Sample Input-1:
---------------
5
2

Sample Output-1:
----------------
3

Explanation: (Refer Hint)
-------------------------
Here are the steps of the game:
1) Start at student 1.
2) Count 2 students clockwise, which are students 1 and 2.
3) student 2 leaves the circle. Next start is student 3.
4) Count 2 students clockwise, which are students 3 and 4.
5) student 4 leaves the circle. Next start is student 5.
6) Count 2 students clockwise, which are students 5 and 1.
7) student 1 leaves the circle. Next start is student 3.
8) Count 2 students clockwise, which are students 3 and 5.
9) student 5 leaves the circle. Only student 3 is left, so they are the winner.

Sample Input-2:
---------------
6
5

Sample Output-2:
----------------
1

Explanation:
------------
Students have roll numbers from 1 to 6. 
Every 5th student is eliminated in a cycle: 5, 4, 6, 2, 3. 
The last remaining is student 1.

Note:
-----
You should design an efficient recursive or iterative solution to handle the largest input values.
*/
import java.util.*;
public class Josephus{
    public static int eliminate(int n, int k){
        int survivor=0;
        for(int i=2;i<=n;i++){
            survivor=(survivor+k)%i;
        }
        return survivor+1;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        System.out.println(eliminate(n,k));
    }
}