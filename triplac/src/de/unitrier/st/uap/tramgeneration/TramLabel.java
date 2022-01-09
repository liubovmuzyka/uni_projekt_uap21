package de.unitrier.st.uap.tramgeneration;

import java.util.List;

public class TramLabel {

    private static int idCounter = 1;
    private int id = idCounter++;
    CodeLine codeLine;

    public CodeLine getCodeLine() {
        return codeLine;
    }

    /** returns parameter */
    public CodeLine setCodeLineVerseVersa(CodeLine codeLine) {
        this.codeLine = codeLine;
        codeLine.getLabels().add(this);
        return codeLine;
    }

    /** returns parameter */
    public List<CodeLine> setCodeLineVerseVersa(List<CodeLine> codeLines) {
        this.codeLine = codeLines.get(0);
        codeLines.get(0).getLabels().add(this);
        return codeLines;
    }

    public String toString() {
        return "L" + id;
    }
}
