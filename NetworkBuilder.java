import java.util.ArrayList;

public class NetworkBuilder {
	// goal: 
	static public void networkBuilder(ArrayList<Node> nodes) {
		ArrayList<Node> myStack = new ArrayList<Node>();
		ArrayList<Node> starters = new ArrayList<Node>();
		ArrayList<Node> enders = new ArrayList<Node>();
		Node lastPopped = null;
		
		//Finding starting nodes
		for(Node node : nodes) {
			if(node.getParents().isEmpty()) {
				starters.add(node);
			} else if(node.getEnd()) {
				enders.add(node);
			} 		
		}
		
		//Building paths
		for(Node node : enders) {
			
			myStack.add(node);
			
			while(!myStack.isEmpty()) {
				
				//Getting the top myStack
				Node top = myStack.get( myStack.size()-1 );
				//System.out.println("top:" + top.getActivityName());
				
				//If the current top of myStack is a starter
				if( starters.contains( top ) ) {
				
					//Print stack
					int totalDuration = 0;
					for(int i = myStack.size()-1;i>=0;i--) {
						System.out.print(myStack.get(i).getActivityName() + ":" + myStack.get(i).getDuration() + " ");
						totalDuration += myStack.get(i).getDuration();
					}
					System.out.print("Total Duration: " + totalDuration);
					System.out.println();
					
					//Pop because we are the start
					lastPopped = top;
					myStack.remove(top);
				} else {
					String nextName = grabNext(top,lastPopped);
					//System.out.println("nextName:" + nextName);
					
					// Because at the end of the children
					if(nextName == null) {
						
						// Pop ourselves because no children
						lastPopped = top;
						myStack.remove( top );					
						
					} else {
						
						Node nextNode = getNodeFromName(nextName,nodes);
						myStack.add(nextNode);
						
					}
				}
			}
		}
	}
	
	static String grabNext(Node top, Node lastPopped) {
		if(lastPopped == null) {
			return top.getParents().get(0);	
		}
		int index = top.getParents().indexOf(lastPopped.getActivityName());
		if(index == top.getParents().size()-1) {
			return null;
		}
		return top.getParents().get(index+1);
	}
	
	static Node getNodeFromName( String name, ArrayList<Node> nodes ) {
		for( Node node : nodes ) {
			if( node.getActivityName().equals( name ) ) {
				return node;
			}
		}
		return null;
	}
}
