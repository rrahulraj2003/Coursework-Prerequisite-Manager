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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }

        StdIn.setFile("src/prereqchecker/" + args[0]);

        //AdjList creation

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

        for(String str : list.keySet()){
            Node n = list.get(str).getNext();
            list.replace(str, list.get(str), n);
        }

        //Validation Process

        StdIn.setFile("src/prereqchecker/" + args[1]);
        StdOut.setFile("src/prereqchecker/" + args[2]);

        String course1 = StdIn.readString(); //cs417
        String course2 = StdIn.readString(); //cs111

        HashMap<String, Boolean> marked = new HashMap<String, Boolean>();

        for(String str : list.keySet()){
            marked.put(str, false);
        }

        isValidPrereq(course2, marked, list);

        
        if(!marked.get(course1)){
            StdOut.print("YES");
        }else{
            StdOut.print("NO");
        }

    }

    public static void isValidPrereq(String course, HashMap<String, Boolean> m, HashMap<String, Node> l){

        m.replace(course, false, true);

        Node n = l.get(course);

        while(n != null){
            if(!m.get(n.getData())){
                isValidPrereq(n.getData(), m, l);
            }
            n = n.getNext();
        }
    }

}
