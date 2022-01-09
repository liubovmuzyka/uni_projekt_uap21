package de.unitrier.st.uap.tramgeneration;

public class AddressPair {
    private Object loc;
    private int nl;
    public AddressPair(Object loc, int nl) {
        this.loc = loc;
        this.nl = nl;
    }

    public Object getLoc() {
        return loc;
    }

    public void setLoc(Object loc) {
        this.loc = loc;
    }

    public int getNl() {
        return nl;
    }

    public void setNl(int nl) {
        this.nl = nl;
    }

    @Override
    public String toString() {
        return "(" + loc.toString() + ", " + nl + ")";
    }
}
