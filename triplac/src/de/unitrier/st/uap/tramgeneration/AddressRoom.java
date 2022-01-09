package de.unitrier.st.uap.tramgeneration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AddressRoom implements Cloneable {

    private Map<String, AddressPair> map = new HashMap<>();
    private int nl = 0;

    public AddressRoom clone() {
        AddressRoom addressRoom = new AddressRoom();
        addressRoom.map = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        addressRoom.nl = nl;
        return addressRoom;
    }

    public int getNl() {
        return nl;
    }

    public void setNl(int nl) {
        this.nl = nl;
        System.out.println(toString());
    }

    private void setObject(String s, Object loc, int nl) {
        map.put(s, new AddressPair(loc, nl));
        System.out.println(toString());
    }

    public void set(String s, TramLabel loc, int nl) {
        setObject(s, loc, nl);
    }

    public void set(String s, Integer loc, int nl) {
        setObject(s, loc, nl);
    }

    public AddressPair get(String s) {
        AddressPair addressPair = map.get(s);
        if(addressPair == null) {
            System.err.println("No value for " + s + " in AddressRoom.");
            System.err.println(toString());
        }
        return addressPair;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AdressRoom {");
        sb.append("\n   nl = " + nl);
        for(Map.Entry<String, AddressPair> entry : map.entrySet()) {
            sb.append("\n   " + entry.getKey() + " -> " + entry.getValue().toString());
        }
        sb.append("\n}");
        return sb.toString();
    }
}
