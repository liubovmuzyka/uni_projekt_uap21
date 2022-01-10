package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.Pair;
import de.unitrier.st.uap.w21.tram.Instruction;

import java.util.ArrayList;
import java.util.List;

public class IdentifierNode extends Node {

    public IdentifierNode(String value) {
        super("identifier", value);
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("IdentifierNode[" + getAttribute() + "].code( )");
        List<CodeLine> code = new ArrayList<>();
        AddressPair addressPair = addressRoom.get((String) getAttribute());
        System.out.println(addressRoom.getNl() +"-"+ addressPair.getNl());
        CodeLine codeLine = new CodeLine(Instruction.LOAD, addressPair.getLoc(), addressRoom.getNl() - addressPair.getNl());
        code.add(codeLine);
        return code;
    }
}
