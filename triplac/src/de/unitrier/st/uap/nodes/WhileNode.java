package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.Instruction;

import java.util.List;

public class WhileNode extends Node {

    public WhileNode() {
        super("while");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("WhileNode.code( )");

        TramLabel tramLabel1 = new TramLabel();
        TramLabel tramLabel2 = new TramLabel();
        TramLabel tramLabel3 = new TramLabel();
        TramLabel tramLabel4 = new TramLabel();
        List<CodeLine> code = child(0).code(addressRoom.clone());
        code.add(new CodeLine(Instruction.IFZERO, tramLabel3));
        code.add(new CodeLine(Instruction.GOTO, tramLabel4));

        code.addAll(tramLabel1.setCodeLineVerseVersa(child(0).code(addressRoom.clone())));
        code.add(new CodeLine(Instruction.IFZERO, tramLabel2));
        code.add(new CodeLine(Instruction.POP));

        code.addAll(tramLabel4.setCodeLineVerseVersa(child(1).code(addressRoom.clone())));
        code.add(new CodeLine(Instruction.GOTO, tramLabel1));
        code.add(tramLabel3.setCodeLineVerseVersa(new CodeLine(Instruction.CONST, null)));
        code.add(tramLabel2.setCodeLineVerseVersa(new CodeLine(Instruction.NOP)));
        return  code;
    }
}
