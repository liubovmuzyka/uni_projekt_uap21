package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.Instruction;


import java.util.List;

public class IfNode extends Node {

    public IfNode() {
        super("if");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("IfNode.code( )");
        List<CodeLine> code = child(0).code(addressRoom.clone());
        TramLabel tramLabel1 = new TramLabel();
        TramLabel tramLabel2 = new TramLabel();
        code.add(new CodeLine(Instruction.IFZERO, tramLabel1));
        code.addAll(child(1).code(addressRoom.clone()));
        code.add(new CodeLine(Instruction.GOTO, tramLabel2));
        List<CodeLine> code2 = child(2).code(addressRoom.clone());
        code2.get(0).getLabels().add(tramLabel1);
        code.addAll(code2);
        CodeLine nop = new CodeLine(Instruction.NOP);
        tramLabel2.setCodeLineVerseVersa(nop);
        code.add(nop);
        return code;
    }
}
