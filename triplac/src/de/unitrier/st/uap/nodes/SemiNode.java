package de.unitrier.st.uap.nodes;

import com.sun.tools.javac.jvm.Code;
import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.tramgeneration.TramLabel;
import de.unitrier.st.uap.w21.tram.Instruction;

import java.util.ArrayList;
import java.util.List;

public class SemiNode extends Node {

    public SemiNode() {
        super("semi");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("SemiNode.code( )");

        ArrayList<CodeLine> code = new ArrayList<>();
        for(int i=0; i<getChildren().size(); i++) {
            if(i>0) {
                code.add(new CodeLine(Instruction.POP));
            }
            code.addAll(getChildren().get(i).code(addressRoom.clone()));
        }
        return code;
    }
}
