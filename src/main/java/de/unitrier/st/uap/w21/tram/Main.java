
package de.unitrier.st.uap.w21.tram;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;


final class Main {
    private Main() {
    }

    public static void main(String[] argv) {

        ArrayList<String> stack = new ArrayList<>();
        String dateiname;
        if (argv[0].equals("-d") || argv[0].equals("-debug")){
            dateiname = argv[1];
        }else {
            dateiname = argv[0];
        }

        Scanner scan = null;
        //reading .tram-file and filling ArrayList with all Instructions
        try {
            scan = new Scanner(new File(dateiname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String line = scan.nextLine();
            stack.add(line);
        }
        scan.close();

        ArrayList<Instruction> instructions = new ArrayList<>();

        Iterator<String> iter = stack.iterator();

        while (iter.hasNext()) {
            String iterstr = iter.next();
            //getting opcode
            String opcode = iterstr.split("[\\\\(||\\\\)]")[0];
            //getting parameters
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


        File f = new File("debug-modus.log");
        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }

        AbstractMachine abstractMachine = new AbstractMachine(instructionsArray);

        Options options = new Options();
        Option debugOption = new Option("d", "debug", false, "Activate debug-modus");
        options.addOption(debugOption);

        CommandLine cmd;
        CommandLineParser parser = new BasicParser();

        try {
            cmd = parser.parse(options, argv);
            if(cmd.hasOption("d")) {
                abstractMachine.setVerbose(true);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(abstractMachine.interpret());
    }
}