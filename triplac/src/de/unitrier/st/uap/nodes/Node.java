package de.unitrier.st.uap.nodes;

import com.sun.tools.javac.util.Pair;
import de.unitrier.st.uap.*;
import de.unitrier.st.uap.tramgeneration.AddressRoom;
import de.unitrier.st.uap.tramgeneration.CodeLine;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Node
{
    private String type;
    private Object attribute;
    private LinkedList<Node> children;

    public Node(String type)
    {
        this.type = type;
        this.attribute = null;
        this.children = new LinkedList<>();
    }

    public Node(String type, Object attribute)
    {
        this.type = type;
        this.attribute = attribute;
        this.children = new LinkedList<>();
    }

    public String getType()
    {
        return this.type;
    }

    public Object getAttribute()
    {
        return this.attribute;
    }

    public LinkedList<Node> getChildren()
    {
        return this.children;
    }

    public Node child(int i) {
        return getChildren().get(i);
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }

    public void setChildren(LinkedList<Node> children)
    {
        this.children = children;
    }

    public void addChild(Node child)
    {
        this.children.add(child);
    }

    public void addChildren(LinkedList<Node> children)
    {
        this.children.addAll(children);
    }

    private String startTag()
    {
        String tag = "<" + type;

        if (attribute != null)
        {
            tag += " attr=\"" + attribute + "\"";
        }

        tag += ">";

        return tag;
    }

    private String endTag()
    {
        return "</" + type + ">";
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder(startTag());
        for (Node node : children)
        {
            str.append(node.toString());
        }
        str.append(endTag());
        return str.toString();
    }

    public abstract List<CodeLine> code(AddressRoom addressRoom);
}
