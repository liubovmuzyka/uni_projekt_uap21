package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.Pair;
import de.unitrier.st.uap.tramgeneration.TramLabel;

import java.util.ArrayList;
import java.util.List;

public class DefDefNode extends Node {

    public DefDefNode() {
        super("def def");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("DefDefNode.code( )");
        // addressRoom = elab_def(this, addressRoom);

        List<CodeLine> code = new ArrayList<>();
        for (Node child : getChildren()) {
            code.addAll(child.code(addressRoom.clone()));
        }
        return code;
    }
    public static AddressRoom elab_def(DefDefNode defDefNode, AddressRoom addressRoom) {
        addressRoom = addressRoom.clone();
        for (Node child : defDefNode.getChildren()) {
            addressRoom = DefinitionNode.elab_def((DefinitionNode) child, addressRoom).u;
        }
        return addressRoom;
    }



}
