package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.w21.tram.Instruction;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefinitionNode extends Node {

    public DefinitionNode() {
        super("def");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("DefinitionNode.code( )");
        //Pair<AddressRoom, TramLabel> elab_def = elab_def(this, addressRoom);
        //addressRoom = elab_def.u;
        //TramLabel definitionTramLabel = elab_def.v;
        TramLabel definitionTramLabel = (TramLabel) addressRoom.get((String) child(0).getAttribute()).getLoc();

        List<String> parameters = new ArrayList<>();
        if(getChildren().get(1) instanceof CommaNode) {
            parameters = getChildren().get(1).getChildren().stream().map(e -> (String) e.getAttribute()).collect(Collectors.toList());
        } else {
            parameters.add((String) getChildren().get(1).getAttribute());
        }
        for(int i=0; i<parameters.size(); i++) {
            addressRoom.set(parameters.get(i), i, addressRoom.getNl() + 1);
        }
        addressRoom.setNl(addressRoom.getNl() + 1);

        List<CodeLine> code = getChildren().get(2).code(addressRoom);

        definitionTramLabel.setCodeLineVerseVersa(code.get(0));
        code.add(new CodeLine(Instruction.RETURN));

        return code;
    }

    public static Pair<AddressRoom, TramLabel> elab_def(DefinitionNode definitionNode, AddressRoom addressRoom) {
        addressRoom = addressRoom.clone();

        String id = (String) definitionNode.getChildren().get(0).getAttribute();
        TramLabel definitionTramLabel = new TramLabel();
        addressRoom.set(id, definitionTramLabel, addressRoom.getNl());

        return new Pair<>(addressRoom, definitionTramLabel);
    }
}
