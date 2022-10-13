package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
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

        //Eligible input read

        StdIn.setFile("src/prereqchecker/" + args[1]);

        HashMap<String, Boolean> doneIt = new HashMap<String, Boolean>();

        for(String str : list.keySet()){
            doneIt.put(str, false);
        }

        int num = StdIn.readInt();
        for(int i = 0; i < num; i++){
            String course = StdIn.readString();

            markDone(course, doneIt, list);
            
        }
        

        //Eligible courses that can be taken

        StdOut.setFile("src/prereqchecker/" + args[2]);
        
        boolean after = false;

        for(String str : list.keySet()){
            
            if(!doneIt.get(str)){
                boolean eligible = true;
                Node n = list.get(str);

                while(n != null){
                    if(!doneIt.get(n.getData())){
                        eligible = false;
                        n = null;
                    }else{
                        n = n.getNext();
                    }
                
                }

                if(eligible){
                    if(after){
                        StdOut.println();
                    }else{
                        after = true;
                    }
                    StdOut.print(str);
                }
            }
            
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
