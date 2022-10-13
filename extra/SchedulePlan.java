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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

        //AdjList creation

        StdIn.setFile(args[0]);

        HashMap<String, Node> list = new HashMap<String, Node>();

        int numCourses = StdIn.readInt();
        for(int i = 0; i < numCourses; i++){
            list.put(StdIn.readString(), new Node(""));
        }
        
        int connections = StdIn.readInt();
        for(int i = 0; i < connections; i++){
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

        StdIn.setFile(args[1]);

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

        //Schedule part

        HashMap<String, Boolean> need = new HashMap<String, Boolean>();
        ArrayList<String> needA = new ArrayList<String>();

        whatNeedToTake(bullsEye, doneIt, list, need);

        for(String str : need.keySet()){
            System.out.println(str);
            needA.add(str);
        }

        boolean clear = false;

        ArrayList<String> answer = new ArrayList<String>();
        answer.add("");

        while(!clear){

            for(String str: needA){
                if(valid(str, doneIt, list) == 0){
                    answer.add(answer.get(0) + str + " ");
                }
                doneIt.replace(str, true);
            }

        }

    }

    public static int valid(String course, HashMap<String, Boolean> d, HashMap<String, Node> l){
        if(course == null) return 0;
        if(d.get(course)){
            return valid(l.get(course).getData(), d, l);
        }else{
            return 1 + valid(l.get(course).getData(), d, l);
        }
    }

    public static void whatNeedToTake(String course, HashMap<String, Boolean> d, HashMap<String, Node> l, HashMap<String, Boolean> ne){
        
        if(d.get(course)) return;

        Node n = l.get(course);

        while(n != null){
            if(!d.get(n.getData())){
                ne.put(n.getData(), true);
            }
            whatNeedToTake(n.getData(), d, l, ne);
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
