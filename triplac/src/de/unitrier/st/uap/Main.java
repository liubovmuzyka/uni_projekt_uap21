package de.unitrier.st.uap;

import de.unitrier.st.uap.nodes.Node;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.err;
import static java.lang.System.out;

final class Main
{
	private Main(){}
	
    public static void main(String[] args)
    {
		if (args.length < 1)
		{
			err.println("Please provide a file.");
			return;
		}
		
        PrintWriter pw = null;
        Node ast;

        try
        {
			String fileName = args[0];
            parser triplaParser = new parser(new Lexer(new FileReader(fileName)));
            ast = ((Node) (triplaParser.parse().value));
            createDot(ast);

			fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
			
			fileName = String.format("%s-ast.xml", fileName);
            pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))));

            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            pw.print(ast.toString());
            out.printf("\"%s\" file created\n", fileName);

        } catch (Exception e)
        {
            err.println(e.getMessage());
        } finally
        {
            if (pw != null)
            {
                pw.close();
            }
        }
    }

    public static void createDot(Node root) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.dot", true))) {
                writer.append("strict digraph graphname\n{");
                visit1(root, writer);
                visit2(root, "", writer);
                writer.append("\n}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void visit1(Node node, BufferedWriter writer) throws IOException {
        Object attribute = node.getAttribute();
        String attributeString = attribute == null ? "" : " (" + attribute.toString() + ")";
        writer.append("\n" + getIDForNode(node) + " [label=\"" + node.getType() + attributeString + "\"];");
        for(Node n : node.getChildren()) {
            visit1(n, writer);
        }
    }

    public static void visit2(Node node, String path, BufferedWriter writer) throws IOException {
        if(path.length() == 0)
            path = "" + getIDForNode(node);
        else
            path += " -> " + getIDForNode(node);
        if(node.getChildren().isEmpty())
            writer.append("\n" + path + ";");
        for(Node n : node.getChildren()) {
            visit2(n, path, writer);
        }
    }

    private static ArrayList<Node> nodeDirectory = new ArrayList<>();
    public static int getIDForNode(Node node) {
        for(int i=0; i<nodeDirectory.size(); i++) {
            if(nodeDirectory.get(i) == node) {
                return i;
            }
        }
        nodeDirectory.add(node);
        return nodeDirectory.size()-1;
    }
}
