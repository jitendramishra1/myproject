package com.org.infix.expression;

import java.util.StringTokenizer;

public class InfixExpression {

	public static void main(String[] args) {
		String expr = "7+(6*5^2+3-4/2)";
        System.out.println("Expression: "+expr);
        System.out.println("Final Result: "+evaluateInfix(expr));

	}
	static boolean isOperand(char c)  
	{  
	    return (c >= '0' && c <= '9');  
	      
	}  
	  
	
	static int value(char c) 
	{  
	    return (int)(c - '0'); 
	      
	}  
	private static int evaluate(String exp)  
	{  
	    // Base Case: Given expression is empty  
	    if (exp.length() == 0) return -1;  
	  
	    // The first character must be  
	    // an operand, find its value  
	    int res = value(exp.charAt(0));  
	  
	    // Traverse the remaining characters in pairs  
	    for (int i = 1; i<exp.length(); i += 2)  
	    {  
	        // The next character must be an operator, and  
	        // next to next an operand  
	        char opr = exp.charAt(i), opd = exp.charAt(i+1);  
	  
	        // If next to next character is not an operand  
	        if (isOperand(opd) == false) return -1;  
	  
	        // Update result according to the operator  
	        if (opr == '+') res += value(opd);  
	        else if (opr == '-') res -= value(opd);  
	        else if (opr == '*') res *= value(opd);  
	        else if (opr == '/') res /= value(opd);  
	  
	        // If not a valid operator  
	        else                 return -1;  
	    }  
	    return res;  
	}  
	private static String evaluateInfix(String expr) {
       int bracket_concave=0;
       int bracket_convex=0;
		expr = expr.replaceAll("\\s+", "");
        Stack<String> stack = new Stack<String>(expr.length());
        String regEx="()^*/+-";
        StringTokenizer tokens = new StringTokenizer(expr, regEx, true);
        while(tokens.hasMoreTokens()){
            String tkn = tokens.nextToken();
            /**read each token and take action**/
            if(tkn.equals("(") 
                    || tkn.matches("[0-9]+") 
                    || tkn.equals("^")
                    || tkn.equals("*")
                    || tkn.equals("/")
                    || tkn.equals("+")
                    || tkn.equals("-")){
            	if(tkn.equals("(")) {
            		bracket_concave++;
            	}
                /**push token to the stack**/
                stack.push(tkn);
            } else if( tkn.equals(")")){
                try {
                	bracket_convex++;
                	StringBuilder strbuilder=new StringBuilder();
                	while(!stack.isStackEmpty()){
                        String strVal=stack.pop();
                        if(strVal.equals("(")){
                        	break;
                        }else {
                        	strbuilder.append(strVal);
                        }
                    }
                    /*int op2 = Integer.parseInt(stack.pop()); 
                    String oprnd = stack.pop();
                    int op1 = Integer.parseInt(stack.pop());

                    if(!stack.isStackEmpty()){
                        stack.pop();
                    }*/
                    int result = evaluate(strbuilder.reverse().toString());
                   /* if(oprnd.equals("^")){
                        result = (int) Math.pow(op1,op2);
                    }else if(oprnd.equals("*")){
                    	result = op1*op2;
                    }else if(oprnd.equals("/")){
                        result = op1/op2;
                    } else if(oprnd.equals("+")){
                        result = op1+op2;
                    } else if(oprnd.equals("-")){
                        result = op1-op2;
                    }*/
                    
                    stack.push(result+"");
                } catch (Exception e) {
                    e.printStackTrace();
                    return "INVALID	EXPRESSION";
                }
            }
        }
        if(bracket_concave !=bracket_convex) {
        	return "INVALID	EXPRESSION";
        }
        String finalResult = "";
        try {
            finalResult = stack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalResult;
	}

}
