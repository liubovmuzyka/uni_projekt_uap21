
package de.unitrier.st.uap.nodes;

import de.unitrier.st.uap.tramgeneration.AddressPair;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;
import de.unitrier.st.uap.Instruction;

import java.util.ArrayList;
import java.util.List;

public class ConstNode extends Node {
    public ConstNode(Integer value) {
        super("const", value);
    }

    @Override
    public List<CodeLine> code(AddressRoom addressRoom) {
        System.out.println("ConstNode.code( )");
        List<CodeLine> code = new ArrayList<>();
        code.add(new CodeLine(Instruction.CONST, getAttribute()));
        return code;
    }
}
