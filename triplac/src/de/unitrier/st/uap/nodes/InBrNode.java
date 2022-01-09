package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.Instruction;

import java.util.ArrayList;
import java.util.List;

public class InBrNode extends Node {

    public InBrNode() {
        super("( )");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("InBrNode.code( )");
        return getChildren().get(0).code(addressRoom.clone());
    }
}
