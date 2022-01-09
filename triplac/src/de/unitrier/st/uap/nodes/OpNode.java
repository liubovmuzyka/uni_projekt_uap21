package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.Instruction;

import java.util.ArrayList;
import java.util.List;

public class OpNode extends Node {

    public OpNode(String op) {
        super ("op", op);
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("OpNode[" + getAttribute() + "].code( )");

        List<CodeLine> code = new ArrayList<>();
        code.addAll(child(0).code(addressRoom.clone()));
        code.addAll(child(1).code(addressRoom.clone()));

        switch ((String) getAttribute()) {
            case "==":
                code.add(new CodeLine(Instruction.EQ));
                break;
            case "!=":
                code.add(new CodeLine(Instruction.NEQ));
                break;
            case "<":
                code.add(new CodeLine(Instruction.LT));
                break;
            case ">":
                code.add(new CodeLine(Instruction.GT));
                break;
            case ">=":
                code.add(new CodeLine(Instruction.GOE));
                break;
            case "<=":
                code.add(new CodeLine(Instruction.LOE));
                break;
            case "&&":
                code.add(new CodeLine(Instruction.MUL));
                break;
            case "||":
                code.add(new CodeLine(Instruction.ADD));
                //TODO
                break;
            case "+":
                code.add(new CodeLine(Instruction.ADD));
                break;
            case "-":
                code.add(new CodeLine(Instruction.SUB));
                break;
            case "/":
                code.add(new CodeLine(Instruction.DIV));
                break;
            case "*":
                code.add(new CodeLine(Instruction.MUL));
                break;

        }
        return code;
    }
}
