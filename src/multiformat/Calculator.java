/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package multiformat;

import javax.swing.*;
import java.util.HashMap;
import java.util.Stack;

/**
 * The multiformat calculator
 */
public class Calculator {

    private Stack<Rational> operandStack = new Stack<>();

    // The current format of the calculator
    private Format format = new FixedPointFormat();
    // The current numberbase of the calculator
    private Base base = new DecimalBase();

    private HashMap<String, Rational> variables = new HashMap<>();

    public void addOperand(String newOperand) throws FormatException, NumberBaseException {
        Rational newRational = format.parse(newOperand, base);
        operandStack.push(newRational);

    }

    public void pushVar(String name){
        Rational var = getVariable(name);
        if (var == null) return;
        Rational zero = new Rational();
        zero.copyOf(var);
        operandStack.push(zero);
    }

    private Rational getVariable(String name) {
        Rational var = variables.get(name);
        if(var==null){
            onException(new Exception("Variabele " + name + " is niet gevonden"));
            return null;
        }
        return var;
    }

    public void popVar(String name){
        Rational var = getVariable(name);
        if (var == null) return;
        var.copyOf(operandStack.pop());
    }

    public void peekVar(String name){
        Rational var = getVariable(name);
        if (var == null) return;
        var.copyOf(operandStack.peek());
    }

    public String listVariables(){
        StringBuilder builder = new StringBuilder();
        for(String varName : variables.keySet()){
            builder.append(varName);
            builder.append(" : ");
            builder.append(format.toString(variables.get(varName), base));
            builder.append("\n");
        }
        return builder.toString();
    }

    public void newVariable(String name){
        variables.put(name,new Rational());
    }

    public void add() {
        if(!hasEnoughOperands())return;
        Rational operand_1 = operandStack.pop();
        Rational operand_0 = operandStack.pop();
        operandStack.push(operand_1.plus(operand_0));
    }

    public void subtract() {
        if(!hasEnoughOperands())return;
        Rational operand_1 = operandStack.pop();
        Rational operand_0 = operandStack.pop();
        operandStack.push(operand_1.minus(operand_0));
    }

    public void multiply() {
        if(!hasEnoughOperands())return;
        Rational operand_1 = operandStack.pop();
        Rational operand_0 = operandStack.pop();
        operandStack.push(operand_1.mul(operand_0));
    }

    private boolean hasEnoughOperands() {
        if(operandStack.size()<2){
            onException(new Exception("Er zijn niet genoeg operanden"));
            return false;
        }
        return true;
    }

    public void delete(){
        operandStack.pop();
    }

    public void divide() {
        if(!hasEnoughOperands())return;
        try {
            Rational operand_1 = operandStack.pop();
            Rational operand_0 = operandStack.pop();
            operandStack.push(operand_1.div(operand_0));
        } catch (ArithmeticException e) {
            onException(e);
        }

    }

    public void onException(Exception e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }

    public String operands(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < operandStack.size(); i++) {
            builder.append(format.toString(operandStack.get(i), base));
            if(i!=operandStack.size()-1)builder.append(", ");
        }
        return builder.toString();
    }

    public String getOperand(int index){
        if(operandStack.size()<=index)return "";
        return format.toString(operandStack.get(index), base);
    }

    public Stack<Rational> getOperandStack() {
        return operandStack;
    }

    public void setBase(Base newBase) {
        base = newBase;
    }

    public Base getBase() {
        return base;
    }

    public void setFormat(Format newFormat) {
        format = newFormat;
    }

    public Format getFormat() {
        return format;
    }
}