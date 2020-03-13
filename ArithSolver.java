///hantao guo
import java.util.*;
public class ArithSolver
{
public static int evaluateExpression(String expression){
       Stack<Character> operator = new Stack<>();
       Stack<Integer> operands = new Stack<>();
       for (char currentChar : expression.toCharArray()){
           if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
               operator.push(currentChar);
           }
           else if (currentChar >= '0' && currentChar <= '9') {
               operands.push(Character.getNumericValue(currentChar));
           }
           else if (currentChar == ')' && operands.size() > 1) {
               Character op = operator.pop();
               int result = 0;
               int firstOperand = operands.pop();
               int secondOperand = operands.pop();
               switch (op){
                   case '+':
                       result = firstOperand + secondOperand;
                       break;
                   case '-':
                       result = firstOperand - secondOperand;
                       break;
                   case '*':
                       result = firstOperand * secondOperand;
                       break;
                   case '/':
                       result = firstOperand / secondOperand;
                       break;
                   default:
                       break;
               }
               operands.push(result);
           }
       }
       return operands.pop();
   }
}
