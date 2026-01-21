package org.visitors;

import org.expression.ListNode;
import org.expression.Node;
import org.expression.NumberNode;
import org.expression.SymbolNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EvaluatorVisitor implements Visitor {

    GlobalEnvironment e = GlobalEnvironment.getInstance();
    static List<String> lst = new ArrayList<>(Arrays.asList("+", "-", "*", "/", ">", "<", ">=", "<=", "if", "sin", "cos", "tan", "sec", "cosec", "cot"));
    static List<String> trigLst = new ArrayList<>(Arrays.asList("sin", "cos", "tan", "sec", "cosec", "cot"));
    static List<String> relationalLst = new ArrayList<>(Arrays.asList(">", "<", ">=", "<="));
    static List<String> arithmeticLst = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));


    @Override
    public String visitNumber(NumberNode n) {
        return n.getNumber();
    }

    @Override
    public String visitSymbol(SymbolNode s) {
        String symbol = s.getSymbol();

        if (lst.contains(symbol)) {
            return symbol;
        }

        return e.getVariable(symbol);

    }

    @Override
    public String visitList(ListNode node) {
        List<Node> lst = node.getList();

        if (lst.isEmpty()) return null;

        Node opNode = lst.get(0);
        String op = opNode.accept(this);

        if (opNode instanceof SymbolNode sym && sym.getSymbol().equals("define")) {
            return defineVariable(lst);
        }

        if (opNode instanceof SymbolNode sym && sym.getSymbol().equals("if")) {
            return lst.get(1).accept(this);
        }

        if (isTrigOpts(op)) {
            return handleTrigOpts(lst);
        }

        if (isRelationalOperator(op)) {
            return handleRelational(lst);
        }

        if (isArithmeticOperator(op)) {
            return handleArithmetic(lst);
        }

        throw new RuntimeException("Something went wrong");
    }

    private String defineVariable(List<Node> lst) {
        String name = ((SymbolNode) lst.get(1)).getSymbol();
        String val = lst.get(2).accept(this);
        e.addVariable(name, val);
        return null;
    }

    private boolean isRelationalOperator(String s) {
        return relationalLst.contains(s);
    }

    private boolean isArithmeticOperator(String s) {
        return arithmeticLst.contains(s);
    }

    private boolean isTrigOpts(String s) {
        return trigLst.contains(s);
    }

    private String handleRelational(List<Node> lst) {

        if (lst.size() > 3) throw new RuntimeException("Invalid expression");

        String op = lst.get(0).accept(this);
        String left = lst.get(1).accept(this);
        String right = lst.get(2).accept(this);
        switch (op) {
            case ">":
                return Double.parseDouble(left) > Double.parseDouble(right) ? "1" : "0";
            case "<":
                return Double.parseDouble(left) < Double.parseDouble(right) ? "1" : "0";
            case ">=":
                return Double.parseDouble(left) >= Double.parseDouble(right) ? "1" : "0";
            case "<=":
                return Double.parseDouble(left) <= Double.parseDouble(right) ? "1" : "0";
            default:
                throw new RuntimeException("Invalid Operator");
        }

    }

    private String handleArithmetic(List<Node> lst) {
        String op = lst.get(0).accept(this);
        String firstOperand = lst.get(1).accept(this);
        double ans = Double.parseDouble(firstOperand);

        switch (op) {
            case "+":
                for (int i = 2; i < lst.size(); i++) {
                    String n = lst.get(i).accept(this);
                    ans += Double.parseDouble(n);
                }
                break;
            case "-":
                for (int i = 2; i < lst.size(); i++) {
                    String n = lst.get(i).accept(this);
                    ans -= Double.parseDouble(n);
                }
                break;
            case "*":
                for (int i = 2; i < lst.size(); i++) {
                    String n = lst.get(i).accept(this);
                    ans *= Double.parseDouble(n);
                }
                break;
            case "/":
                for (int i = 2; i < lst.size(); i++) {
                    String n = lst.get(i).accept(this);
                    if(n.equals("0.0") || n.equals("0")) throw new ArithmeticException("Divide by zero");
                    ans /= Double.parseDouble(n);
                }
                break;
            default:
                throw new RuntimeException("Invalid Operator");
        }
        return String.valueOf((int) ans);

    }

    private String handleTrigOpts(List<Node> lst) {
        String op = lst.get(0).accept(this);
        String operand = lst.get(1).accept(this);
        switch (op) {
            case "sin":
                return String.valueOf(Math.sin(Double.parseDouble(operand)));
            case "cos":
                return String.valueOf(Math.cos(Double.parseDouble(operand)));
            case "tan":
                return String.valueOf(Math.tan(Double.parseDouble(operand)));
            case "cosec":
                return String.valueOf(1 / Math.sin(Double.parseDouble(operand)));
            case "sec":
                return String.valueOf(1 / Math.cos(Double.parseDouble(operand)));
            case "cot":
                return String.valueOf(1 / Math.tan(Double.parseDouble(operand)));
            default:
                throw new RuntimeException("Invalid Operator");
        }
    }

}
