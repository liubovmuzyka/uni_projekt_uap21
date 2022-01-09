package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.Instruction;


import java.util.ArrayList;
import java.util.List;

public class LetNode extends Node {

    public LetNode() {
        super("let");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("LetNode.code( )");

        List<CodeLine> code = new ArrayList<>();
        TramLabel tramLabel = new TramLabel();

        if(child(0) instanceof DefDefNode) {
            addressRoom = DefDefNode.elab_def((DefDefNode) child(0), addressRoom);
        } else if(child(0) instanceof DefinitionNode) {
            addressRoom = DefinitionNode.elab_def((DefinitionNode) child(0), addressRoom).u;
        }

        code.add(new CodeLine(Instruction.GOTO, tramLabel));
        code.addAll(child(0).code(addressRoom.clone()));
        List<CodeLine> codeLines = child(1).code(addressRoom.clone());
        tramLabel.setCodeLineVerseVersa(codeLines.get(0));
        code.addAll(codeLines);

        return code;
    }
}
