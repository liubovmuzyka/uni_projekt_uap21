package de.unitrier.st.uap.tramgeneration;

import de.unitrier.st.uap.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CodeLine {

    private ArrayList<TramLabel> labels = new ArrayList<>();
    private int opcode;
    private Object arg1TramLabelOrInteger;
    private Object arg2TramLabelOrInteger;
    private Integer arg3;

    public CodeLine(int opcode, Object arg1TramLabelOrInteger, Object arg2TramLabelOrInteger, Integer arg3) {
        this.opcode = opcode;
        this.arg1TramLabelOrInteger = arg1TramLabelOrInteger;
        this.arg2TramLabelOrInteger = arg2TramLabelOrInteger;
        this.arg3 = arg3;
    }

    public CodeLine(int opcode) {
        this(opcode, null, null, null);
    }

    public CodeLine(int opcode, Object arg1TramLabelOrInteger) {
        this(opcode, arg1TramLabelOrInteger, null, null);
    }

    public CodeLine(int opcode, Object arg1TramLabelOrInteger, Object arg2TramLabelOrInteger) {
        this(opcode, arg1TramLabelOrInteger, arg2TramLabelOrInteger, null);
    }

    public Instruction toInstruction(List<CodeLine> completeCode) {

        Integer arg1 = null;
        if(arg1TramLabelOrInteger instanceof TramLabel) {
            TramLabel arg1TramLabel = (TramLabel) arg1TramLabelOrInteger;
            for(int i=0; i<completeCode.size(); i++) {
                if(completeCode.get(i).labels.contains(arg1TramLabel)) {
                    arg1 = i;
                    break;
                }
            }
        } else if(arg1TramLabelOrInteger instanceof Integer) {
            arg1 = (Integer) arg1TramLabelOrInteger;
        }
        Integer arg2 = null;
        if(arg2TramLabelOrInteger instanceof TramLabel) {
            TramLabel arg2TramLabel = (TramLabel) arg2TramLabelOrInteger;
            for(int i=0; i<completeCode.size(); i++) {
                if(completeCode.get(i).labels.contains(arg2TramLabel)) {
                    arg2 = i;
                    break;
                }
            }
        } else if(arg2TramLabelOrInteger instanceof Integer) {
            arg2 = (Integer) arg2TramLabelOrInteger;
        }

        return new Instruction(opcode, arg1, arg2, arg3);
    }

    public String toReadableInstruction(List<CodeLine> completeCode) {
        Instruction instruction = toInstruction(completeCode);
        String instructionString = instruction.toString();
        if(instructionString.contains(" "))
            instructionString = instructionString.substring(0, instructionString.indexOf(" "));
        if (arg1TramLabelOrInteger != null || opcode == Instruction.CONST) {
            instructionString += " " + (arg1TramLabelOrInteger == null ? "NULL" : arg1TramLabelOrInteger);
            if (arg2TramLabelOrInteger != null) {
                instructionString += " " + arg2TramLabelOrInteger.toString();
                if (arg3 != null) {
                    instructionString += " " + arg3;
                }
            }
        }
        if(labels.size() != 0) {
            instructionString = labels.stream().map(e -> e.toString()).collect(Collectors.joining(", ")) + ": " + instructionString;
        }
        return instructionString;
    }

    public ArrayList<TramLabel> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<TramLabel> labels) {
        this.labels = labels;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public Object getArg1TramLabelOrInteger() {
        return arg1TramLabelOrInteger;
    }

    public void setArg1TramLabelOrInteger(Object arg1TramLabelOrInteger) {
        this.arg1TramLabelOrInteger = arg1TramLabelOrInteger;
    }

    public Object getArg2TramLabelOrInteger() {
        return arg2TramLabelOrInteger;
    }

    public void setArg2TramLabelOrInteger(Object arg2TramLabelOrInteger) {
        this.arg2TramLabelOrInteger = arg2TramLabelOrInteger;
    }

    public Integer getArg3() {
        return arg3;
    }

    public void setArg3(Integer arg3) {
        this.arg3 = arg3;
    }
}
