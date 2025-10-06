/*
In a university examination, one clever student tried to trick the evaluator.
The student wrote the same question multiple times in the answer sheet, 
thinking that repeating might fetch more marks.

But the evaluator’s checking system is smart!
The system should:
Keep the questions in the order they are written (like the exam paper).

If a question is written more than once, 
only the last answer should be considered (previous attempts overwritten).

Implement this system using a LinkedHashMap, where:

Key = Question Number
Value = Student’s Answer

case = 1 
input = Q1=A1, Q2=A2, Q1=A3 
output = {Q1=A3, Q2=A2}

case = 2 
input = Q1=A, Q2=B, Q3=C, Q2=D 
output = {Q1=A, Q2=D, Q3=C}
*/
import java.util.*;
class EvaluateQuestions{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        String paper=sc.nextLine();
        String[] questions=paper.trim().split("[ ,]+");
        Map<String, String> map=new LinkedHashMap<>();
        for(String question: questions){
            String[] parts=question.split("=");
            map.put(parts[0],parts[1]);
        }
        System.out.println(map);
    }
}