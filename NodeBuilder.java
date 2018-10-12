
import java.util.ArrayList;
import java.util.Scanner;

public class NodeBuilder {

	public static void main(String[] args) {
		//Initialize ArrayList to store created nodes
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		Scanner kb = new Scanner(System.in);
		
		// Initializing node variables
		String activityName;
		int duration;
		ArrayList<String> parents = new ArrayList<String>();
		
		String input = "";
		
		boolean stop = false;
		int i = 0;
		do
		{
			//Receive user node input
			System.out.println("Enter Node Information.");
			input = kb.nextLine();
			
			
			//Setting up new Scanner and delimiter
			Scanner s = new Scanner(input);
			s.useDelimiter(",");
			
			//Set node variables with user input
			if(s.hasNext()) {
				activityName = s.next();
				//System.out.println(activityName);
			} else
				activityName = "Activity " + i;
			
			if(s.hasNext()) {
				duration = s.nextInt();
				//System.out.println(duration);
			} else
				duration = 0;
			
			
			while (s.hasNext()) {
				parents.add(s.next());
			}
			
			if(!activityName.equals("stop")) {
				Node myNode = new Node(activityName, duration, parents);
				nodes.add(myNode);
			} else {
				stop = true;
			}
			
			//System.out.println(parents.size());
			//System.out.println(nodes.size());
			
			//reset parents ArrayList
			parents.clear();
			
			//Closing Scanner
			s.close();
			
			i += 1;
			
		} while (!stop);
		
		for(Node node : nodes) {
			node.setEnd( nodes );
		}
		
//		for(int x=0;x<nodes.size();x++) {
//			System.out.println(nodes.get(x));
//		}
		
		NetworkBuilder.networkBuilder(nodes);
		
		kb.close();
	}

}
