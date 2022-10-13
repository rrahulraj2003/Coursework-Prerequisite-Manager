package prereqchecker;

import java.util.*;

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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToComplete {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
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

        //MarkDoneIt process

        StdIn.setFile("src/prereqchecker/" + args[1]);

        String bullsEye = StdIn.readString();

        HashMap<String, Boolean> doneIt = new HashMap<String, Boolean>();

        for(String str : list.keySet()){
            doneIt.put(str, false);
        }

        int num = StdIn.readInt();
        for(int i = 0; i < num; i++){
            String course = StdIn.readString();

            markDone(course, doneIt, list);
            
        }

        //NeedToTake process

        StdOut.setFile("src/prereqchecker/" + args[2]);

        whatNeedToTake(bullsEye, doneIt, list);

    }

    public static void whatNeedToTake(String course, HashMap<String, Boolean> d, HashMap<String, Node> l){
        
        if(d.get(course)) return;

        Node n = l.get(course);

        while(n != null){
            if(!d.get(n.getData())){
                StdOut.println(n.getData());
            }
            whatNeedToTake(n.getData(), d, l);
            n = n.getNext();
        }
    }

    public static void markDone(String course, HashMap<String, Boolean> d, HashMap<String, Node> l){
        d.replace(course, false, true);

        Node n = l.get(course);
        
        while(n != null){
            markDone(n.getData(), d, l);
            n = n.getNext();
        }

    }
}
