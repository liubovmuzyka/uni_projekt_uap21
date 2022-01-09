package de.unitrier.st.uap.nodes;

import com.sun.tools.javac.util.FatalError;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;

import java.util.List;

public class CommaNode extends Node {

    public CommaNode() {
        super("comma");
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("CommaNode.code( )");
        throw new FatalError("do not call code( ) of CommaNode");
    }
}
