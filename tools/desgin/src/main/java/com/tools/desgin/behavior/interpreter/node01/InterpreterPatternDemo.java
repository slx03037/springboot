package com.tools.desgin.behavior.interpreter.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 11:18
 */
public class InterpreterPatternDemo {

    public static Expression getMaleExpression(){
        TerminalExpression robert = new TerminalExpression("Robert");
        TerminalExpression john = new TerminalExpression("John");
        return new OrExpression(robert,john);
    }

    public static Expression getMarriedWomanExpression(){
        Expression julie=new TerminalExpression("Julie");
        Expression married=new TerminalExpression("Married");
        return new AndExpression(julie,married);
    }

    public static void main(String[]args){
        Expression maleExpression = getMaleExpression();
        Expression marriedWomanExpression = getMarriedWomanExpression();

        System.out.println("John is male:"+maleExpression.interpret("John"));
        System.out.println("Julie is a married women?"+marriedWomanExpression.interpret("Married Julie"));
    }
}
