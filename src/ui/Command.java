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
package ui;

import multiformat.*;

import javax.swing.*;
import java.io.*;

/**
 * De main-klasse die leest en schrijft naar de console.
 *
 * @author Brugali
 * @author Balj�
 */
public class Command {
    Calculator calc = new Calculator();
    BufferedReader prevReader = null;
    BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in));

    boolean nextCommand() {
        System.out.print("\n[" + calc.getBase().getName() + ","
                + calc.getFormat().getName() + ","
                + calc.operands()
                + "] >");
        try {
            // reads the command from the keyboard
            String command = lineReader.readLine();
            while (command == null) {
                if (prevReader != null) {
                    lineReader = prevReader;
                    prevReader = null;
                    command = lineReader.readLine();
                } else {
                    return false;
                }
            }
            if (command.equals("+")) calc.add();
            else if (command.equals("-")) calc.subtract();
            else if (command.equals("*")) calc.multiply();
            else if (command.equals("/")) calc.divide();
            else if (command.equals("dec")) calc.setBase(new DecimalBase());
            else if (command.equals("bin")) calc.setBase(new BinaryBase());
            else if (command.equals("oct")) calc.setBase(new OctalBase());
            else if (command.equals("hex")) calc.setBase(new HexBase());
            else if (command.equals("vars")) System.out.println(calc.listVariables());
            else if (command.equals("rat")) calc.setFormat(new RationalFormat());
            else if (command.equals("fixed")) calc.setFormat(new FixedPointFormat());
            else if (command.equals("float")) calc.setFormat(new FloatingPointFormat());
            else if (command.equals("del")) calc.delete();
            else if (command.indexOf("pushvar") >= 0) {
                calc.pushVar(command.substring(7).trim());
            } else if (command.indexOf("peekvar") >= 0) {
                calc.peekVar(command.substring(7).trim());
            } else if (command.indexOf("popvar") >= 0) {
                calc.popVar(command.substring(6).trim());
            } else if (command.indexOf("var") >= 0) {
                calc.newVariable(command.substring(3).trim());
            } else if (command.indexOf("op") >= 0) {
                try {
                    calc.addOperand(command.substring(2).trim());
                } catch (FormatException e) {
                    System.out.println("Wrong operand: " + e.getMessage());
                } catch (NumberBaseException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.indexOf("read") >= 0) {
                try {
                    BufferedReader file = new BufferedReader(
                            new FileReader(command.substring(4).trim()));
                    prevReader = lineReader;
                    lineReader = file;
                    System.out.println("Reading from file " + command.substring(4).trim());
                } catch (Exception e) {
                    System.out.println("Cannot open file " + command.substring(4).trim());
                }
            } else if (command.equals("help")) {
                printHelp();
            } else if (command.equals("exit"))
                return false;
            else {
                System.out.println("Error! Not a valid command");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return true;
    }

    public String getCalculatorState() {
        return "[" + calc.getBase().getName() + ","
                + calc.getFormat().getName() + ","
                + calc.operands()
                + "] >";
    }

    public void parseCommand(String command) throws FileNotFoundException, FormatException, InvalidCommandException, NumberBaseException {
        if (command.equals("+")) calc.add();
        else if (command.equals("-")) calc.subtract();
        else if (command.equals("*")) calc.multiply();
        else if (command.equals("/")) calc.divide();
        else if (command.equals("dec")) calc.setBase(new DecimalBase());
        else if (command.equals("bin")) calc.setBase(new BinaryBase());
        else if (command.equals("oct")) calc.setBase(new OctalBase());
        else if (command.equals("hex")) calc.setBase(new HexBase());
        else if (command.equals("vars")) System.out.println(calc.listVariables());
        else if (command.equals("rat")) calc.setFormat(new RationalFormat());
        else if (command.equals("fixed")) calc.setFormat(new FixedPointFormat());
        else if (command.equals("float")) calc.setFormat(new FloatingPointFormat());
        else if (command.equals("del")) calc.delete();
        else if (command.indexOf("pushvar") >= 0) {
            calc.pushVar(command.substring(7).trim());
        } else if (command.indexOf("peekvar") >= 0) {
            calc.peekVar(command.substring(7).trim());
        } else if (command.indexOf("popvar") >= 0) {
            calc.popVar(command.substring(6).trim());
        } else if (command.indexOf("var") >= 0) {
            calc.newVariable(command.substring(3).trim());
        } else if (command.indexOf("op") >= 0) {
            calc.addOperand(command.substring(2).trim());
        } else if (command.indexOf("read") >= 0) {
            BufferedReader file = new BufferedReader(
                    new FileReader(command.substring(4).trim()));
            prevReader = lineReader;
            lineReader = file;
            System.out.println("Reading from file " + command.substring(4).trim());
        } else if (command.equals("help")) {
            printHelp();
        } else if (command.equals("exit")) {
            System.exit(1);
        } else {
            System.out.println("Error! Not a valid command");
            throw new InvalidCommandException();
        }
    }

    void printHelp() {

        String message = "Insert one of the following commands:";
        message += "\r\n\r\n";
        message += "  op <numero>  (store an operand)";
        message += "\r\n";
        message += "  var <name>  (create a new variable set to 0)";
        message += "\r\n";
        message += "  pushvar <name>  (add the value of the variable as an operand)";
        message += "\r\n";
        message += "  popvar <name>  (pop the last operand into a variable)";
        message += "\r\n";
        message += "  peekvar <name>  (peek the last operand into a variable)";
        message += "\r\n";
        message += "  +            (sum the last two operands)";
        message += "\r\n";
        message += "  -            (substract the last operand from the previous one)";
        message += "\r\n";
        message += "  *            (multiply the last two operands)";
        message += "\r\n";
        message += "  /            (divide the last two operands)";
        message += "\r\n";
        message += "  dec          (switch to base 10)";
        message += "\r\n";
        message += "  bin          (switch to binary base)";
        message += "\r\n";
        message += "  hex          (switch to hexadecimal base)";
        message += "\r\n";
        message += "  oct          (switch to octadecimal base)";
        message += "\r\n";
        message += "  fixed        (switch to fixed point format)";
        message += "\r\n";
        message += "  float        (switch to floating point format)";
        message += "\r\n";
        message += "  rat          (switch to rational format)";
        message += "\r\n";
        message += "  del          (remove last operand)";
        message += "\r\n";
        message += "  help         (print this command list)";
        message += "\r\n";
        message += "  exit         (terminate execution)";
        message += "\r\n";

        System.out.println(message);

        JOptionPane.showMessageDialog(null, message, "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getVariables() {
        return calc.listVariables();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        View view = new View();
        Command command = new Command();
        while (command.nextCommand()) ;
        System.out.println("Thanks for using our calculator!");
    }
}
