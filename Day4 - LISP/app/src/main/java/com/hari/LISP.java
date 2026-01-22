package com.hari;

import com.hari.expression.Node;
import com.hari.visitors.EvaluatorVisitor;
import com.hari.parser.Parser;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LISP {
    public static void main(String[] args) {
        String EXIT_COMMAND = "exit";
        Parser p = new Parser();
        EvaluatorVisitor ev = new EvaluatorVisitor();
        Logger log = Logger.getLogger(LISP.class.getName());
        String input;

        try(Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (true) {
                if(log.isLoggable(Level.FINE)){
                    log.fine(">>");
                }
                input = sc.nextLine();
                if ((EXIT_COMMAND).equalsIgnoreCase(input)) {
                    break;
                }
                try {
                    Node n = p.parse(input);
                    if(log.isLoggable(Level.FINE)) {
                        log.fine(n.accept(ev));
                    }
                } catch (Exception e) {
                    if(log.isLoggable(Level.FINE)) {
                        log.fine(e.getMessage());
                    }
                }
            }
        }
    }
}