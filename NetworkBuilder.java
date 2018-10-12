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
				
				//If the current top of myStack is a starter
				if( starters.contains( top ) ) {
				
					// Print stack
					System.out.println( myStack );
					
					// Pop because we are the start
					lastPopped = top;
					myStack.remove(top);
				} else {
					String nextName = grabNext(top,lastPopped);
					
					// Because at the end of the children
					if(nextName.isEmpty()) {
						
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
