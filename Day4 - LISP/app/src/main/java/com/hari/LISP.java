package com.hari.lisp;

import com.hari.expression.Node;
import com.hari.visitors.EvaluatorVisitor;
import java.util.Scanner;

public class LISP {
    public static void main(String[] args) {
        Parser p = new Parser();
        EvaluatorVisitor ev = new EvaluatorVisitor();
        Scanner sc = new Scanner(System.in);

        String input;

        while(true){
            System.out.println(">>");
            input = sc.nextLine();
            if(input.equalsIgnoreCase("exit")){
                break;
            }
            try{
                Node n = p.parse(input);
                System.out.println(n.accept(ev))
            }catch (Exception e){
                System.out.println(e);
            }
        }

        sc.close();
    }
}