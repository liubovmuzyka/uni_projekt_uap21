
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
        if (argv[0].equals("-d") || argv[0].equals("-debug")) {
            dateiname = argv[1];
        } else {
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
            String opcode = iterstr.split("\\s+")[0];
            //getting parameters
            String parameters;
            String pr1 = null, pr2 = null, pr3 = null;
            try {
                pr1 = iterstr.split("\\s+")[1].length() >= 1 ? iterstr.split("\\s+")[1] : null;
                pr2 = iterstr.split("\\s+")[2].length() >= 1 ? iterstr.split("\\s+")[2] : null;
                pr3 = iterstr.split("\\s+")[3].length() >= 1 ? iterstr.split("\\s+")[3] : null;

            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            Instruction instruction = null;
            switch (opcode) {
                case "CONST":
                    instruction = new Instruction(Instruction.CONST, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "CONST NULL":
                    instruction = new Instruction(Instruction.CONSTNULL, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "LOAD":
                    instruction = new Instruction(Instruction.LOAD, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "STORE":
                    instruction = new Instruction(Instruction.STORE, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "ADD":
                    instruction = new Instruction(Instruction.ADD, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "SUB":
                    instruction = new Instruction(Instruction.SUB, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "MUL":
                    instruction = new Instruction(Instruction.MUL, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "DIV":
                    instruction = new Instruction(Instruction.DIV, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "LT":
                    instruction = new Instruction(Instruction.LT, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "LOE":
                    instruction = new Instruction(Instruction.LOE, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "GT":
                    instruction = new Instruction(Instruction.GT, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "GOE":
                    instruction = new Instruction(Instruction.GOE, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "EQ":
                    instruction = new Instruction(Instruction.EQ, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "NEQ":
                    instruction = new Instruction(Instruction.NEQ, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "IFZERO":
                    instruction = new Instruction(Instruction.IFZERO, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "GOTO":
                    instruction = new Instruction(Instruction.GOTO, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "HALT":
                    instruction = new Instruction(Instruction.HALT, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "NOP":
                    instruction = new Instruction(Instruction.NOP, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "INVOKE":
                    instruction = new Instruction(Instruction.INVOKE, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "RETURN":
                    instruction = new Instruction(Instruction.RETURN, pr1 != null ? Integer.parseInt(pr1) : null,
                            pr2 != null ? Integer.parseInt(pr2) : null,
                            pr3 != null ? Integer.parseInt(pr3) : null);
                    instructions.add(instruction);
                    break;
                case "POP":
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
            System.out.println(instructionsArray[i].toString());
            i++;
        }

        File f = new File("debug-modus.log");
        if (f.exists() && !f.isDirectory()) {
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
            if (cmd.hasOption("d")) {
                abstractMachine.setVerbose(true);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(abstractMachine.interpret());
    }
}