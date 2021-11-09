
package de.unitrier.st.uap.w21.tram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


final class Main {
    private Main() {
    }

    public static void main(String[] argv) {
        // TODO: Create an instance of the abstract machine with respective parameters
        //System.out.println(new AbstractMachine(Instruction.program1).interpret());

        ArrayList<String> stack = new ArrayList<>();
        String dateiname = argv[0];
        Scanner scan = null;
        try {
            scan = new Scanner(new File(dateiname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String linesplit = line.split(":")[1];
            stack.add(linesplit);
        }
        scan.close();
        ArrayList<Instruction> instructions = new ArrayList<>();
        Iterator<String> iter = stack.iterator();
        while (iter.hasNext()) {

            String iterstr = iter.next();
            String opcode = iterstr.split("[\\\\(||\\\\)]")[0];
            String parameters;
            String pr1 = null, pr2 = null, pr3 = null;

            try {
                if (iterstr.indexOf("(")!=-1) {
                    parameters = iterstr.split("[\\(||\\)]")[1];
                    String[] prs = parameters.split(",");

                    pr1 = prs.length >= 1 ? prs[0] : null;
                    pr2 = prs.length >= 2 ? prs[1] : null;
                    pr3 = prs.length >= 3 ? prs[2] : null;
               }

            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            Instruction instruction = null;
            switch (opcode) {
                case "const":
                    instruction = new Instruction(Instruction.CONST, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "load":
                    instruction = new Instruction(Instruction.LOAD, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "store":
                    instruction = new Instruction(Instruction.STORE, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "add":
                    instruction = new Instruction(Instruction.ADD, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "sub":
                    instruction = new Instruction(Instruction.SUB, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "mul":
                    instruction = new Instruction(Instruction.MUL, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "div":
                    instruction = new Instruction(Instruction.DIV, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "lt":
                    instruction = new Instruction(Instruction.LT, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "gt":
                    instruction = new Instruction(Instruction.GT, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "eq":
                    instruction = new Instruction(Instruction.EQ, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "neq":
                    instruction = new Instruction(Instruction.NEQ, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "ifzero":
                    instruction = new Instruction(Instruction.IFZERO, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "goto":
                    instruction = new Instruction(Instruction.GOTO, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "halt":
                    instruction = new Instruction(Instruction.HALT, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "nop":
                    instruction = new Instruction(Instruction.NOP, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "invoke":
                    instruction = new Instruction(Instruction.INVOKE, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "return":
                    instruction = new Instruction(Instruction.RETURN, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "pop":
                    instruction = new Instruction(Instruction.POP, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
            }
        }
        Instruction[] instructionsArray = new Instruction[instructions.size()];
        int i = 0;
        Iterator<Instruction> instructionIterator = instructions.iterator();
        while (instructionIterator.hasNext()) {
            instructionsArray[i] = instructionIterator.next();
            i++;
        }

        AbstractMachine abstractMachine = new AbstractMachine(instructionsArray);
        System.out.println(abstractMachine.interpret());
    }
}