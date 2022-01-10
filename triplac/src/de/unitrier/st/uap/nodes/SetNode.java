package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.w21.tram.Instruction;

import java.util.ArrayList;
import java.util.List;

public class SetNode extends Node {

    public SetNode() {
        super("set");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("SetNode.code( )");
        List<CodeLine> code = new ArrayList<>();
        AddressPair addressPair = addressRoom.get((String) child(0).getAttribute());
        code.addAll(getChildren().get(1).code(addressRoom.clone()));
        code.add(new CodeLine(Instruction.STORE, addressPair.getLoc(), addressRoom.getNl() - addressPair.getNl()));
        code.add(new CodeLine(Instruction.LOAD, addressPair.getLoc(), addressRoom.getNl() - addressPair.getNl()));
        return code;
    }
}
