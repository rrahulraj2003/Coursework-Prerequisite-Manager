package prereqchecker;

import java.util.HashMap;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

        StdIn.setFile("src/prereqchecker/" + args[0]);
        StdOut.setFile("src/prereqchecker/" + args[1]);

        HashMap<String, Node> list = new HashMap<String, Node>();

        int numCourses = StdIn.readInt();
        for(int i = 0; i < numCourses; i++){
            list.put(StdIn.readString(), new Node(""));
        }
        
        while(StdIn.hasNextLine()){
            String temp = StdIn.readString();
            Node n = list.get(temp);

            while(n.hasNext()) n = n.getNext();
            
            n.setNext(new Node(StdIn.readString()));
            
        }

        int length = 1;

        for(String str : list.keySet()){
            StdOut.print(str + ": ");
            Node n = list.get(str).getNext();
            list.replace(str, list.get(str), n);
            if(n != null){
                StdOut.print(n.getData());
                n = n.getNext();
            }
            while(n != null){
                StdOut.print(" -> " + n.getData());
                n = n.getNext();
            }
            if(length != list.size()){
                length++;
                StdOut.println();
            }
        }
	
    }
}
