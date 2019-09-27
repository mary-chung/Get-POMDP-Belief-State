import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    private static Stack frontier = new Stack();
    private static int numVariables = 3;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // add to the frontier an empty assignment to the variables
        List<Boolean> emptyAssignment = new ArrayList<>();
        frontier.push(emptyAssignment);
        // perform DFS
        DFS();
    }

    private static boolean constraintsSatisfied(List<Boolean> assignment) {
        if (assignment.size() == 0 | assignment.size() == 1) {
            return true;
        } else if (assignment.size() == numVariables) {
            if  (assignment.get(0) != assignment.get(1) & assignment.get(1) != assignment.get(2)) {
                System.out.println("X=" + assignment.get(0) + " Y=" + assignment.get(1) + " Z=" + assignment.get(2) + " Solution");
                return true;
            } else {
                System.out.println("X=" + assignment.get(0) + " Y=" + assignment.get(1) + " Z=" + assignment.get(2) + " Fail");
            }
        } else if (assignment.size() == 2) {
            if (assignment.get(0) != assignment.get(1)) {
                System.out.println("X=" + assignment.get(0) + " Y=" + assignment.get(1));
                return true;
            } else {
                System.out.println("X=" + assignment.get(0) + " Y=" + assignment.get(1) + " Fail");
            }
        }
        return false;
    }


    private static void DFS() {

        while (!frontier.empty()) {
            List assignmentToCheck = (List)frontier.pop();
            if (constraintsSatisfied(assignmentToCheck)) {
                // add 2 neighbours to the frontier only if assignmentToCheck is not failed branch
                // create 2 lists for each neighbour with old values from assignmentToCheck and a new variable assignment
                List<Boolean> neighbour1 = new ArrayList<>();
                List<Boolean> neighbour2 = new ArrayList<>();

                for (int i=0; i < assignmentToCheck.size(); i++) {
                    neighbour1.add((Boolean)assignmentToCheck.get(i));
                    neighbour2.add((Boolean)assignmentToCheck.get(i));
                }
                if (neighbour1.size() < numVariables) {
                    neighbour1.add(true);
                    neighbour2.add(false);
                    frontier.push(neighbour1);
                    frontier.push(neighbour2);
                }
            }
            // recurse through tree
            //DFS();
        }
    }
}
