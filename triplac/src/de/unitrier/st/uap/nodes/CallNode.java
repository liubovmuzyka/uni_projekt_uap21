package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.Instruction;

import java.util.ArrayList;
import java.util.List;

public class CallNode extends Node {

    public CallNode() {
        super("call");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("CallNode.code( )");
        List<CodeLine> code = new ArrayList<>();
        AddressPair addressPair = addressRoom.get((String) child(0).getAttribute());
        if (child(1) instanceof CommaNode) {
            for (Node parameter : child(1).getChildren()) {
                code.addAll(parameter.code(addressRoom.clone()));
            }
        } else {
            code.addAll(child(1).code(addressRoom.clone()));
        }
        code.add(new CodeLine(Instruction.INVOKE,
                child(1) instanceof CommaNode ? child(1).getChildren().size() : 1,
                addressPair.getLoc(),
                addressRoom.getNl() - addressPair.getNl()));

        return code;
    }
}
