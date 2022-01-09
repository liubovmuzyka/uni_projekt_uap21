package de.unitrier.st.uap;

import de.unitrier.st.uap.nodes.CommaNode;
import de.unitrier.st.uap.nodes.DefDefNode;
import de.unitrier.st.uap.nodes.Node;
import de.unitrier.st.uap.nodes.SemiNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SyntaxTreeSimplifier {

    public static void simplify(Node tree) {
        List<Node> copy = tree.getChildren().stream().collect(Collectors.toList());
        for(Node child : copy) {
            simplifyStep(tree, child);
        }
    }

    private static void simplifyStep(Node parent, Node child) {
        List<Node> copy = child.getChildren().stream().collect(Collectors.toList());
        for(Node childOfChild : copy) {
            simplifyStep(child, childOfChild);
        }
        if(parent instanceof SemiNode && child instanceof SemiNode
                || parent instanceof CommaNode && child instanceof CommaNode
                || parent instanceof DefDefNode && child instanceof DefDefNode) {

            child.getChildren().addAll(parent.getChildren());
            child.getChildren().remove(child);
            parent.setChildren(child.getChildren());
        }
    }

    /** u=true if an simlification was maid */
    /*public Pair<Boolean, List<Node>> simplifyStep(Node tree) {
        boolean changed = false;
        LinkedList<Node> newList = new LinkedList<>();
        LinkedList<Node> list = tree.getChildren();
        for(Node node : list) {
            Pair<Boolean, List<Node>> result = simplifyStep(node);
            if (result.u == true) {
                changed = true;
            }
            newList.addAll(result.v);
        }
        if(tree instanceof DefDefNode || tree instanceof SemiNode || tree instanceof CommaNode) {
            return new Pair<>(Boolean.TRUE, tree.getChildren());
        } else {
            return new Pair<>(changed, Arrays.asList(tree));
        }
    }*/


}
