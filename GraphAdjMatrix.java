import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class GraphAdjMatrix implements Graph{
	Node[] nodes;
	public GraphAdjMatrix(int n){
		nodes = new Node[n];
		for(int i=0; i<n; i++){
			nodes[i] = null;
		}
	}
	@Override
	public void addEdge(int v1, int v2, int weight) {
		if(nodes[v1]==null){
			Node newNode = new Node(v2, weight);
			nodes[v1] = newNode;
		}else{
			Node newNode = new Node(v2, weight);
			Node oldHead = nodes[v1];
			oldHead.previous = newNode;
			newNode.next = oldHead;
			nodes[v1]=newNode;
		}
		
	}
	@Override
	public int getEdge(int v1, int v2) {
		if(v1<nodes.length && v2<nodes.length){
			//System.out.println("orhere");
			Node n = nodes[v1];
			while(n!=null){
				if(n.elem==v2){
					return n.weight;
				}
				n=n.next;
			}
		}
		return 0;
	}
	@Override
	public int createSpanningTree() {
		int result = 0;
		boolean[] visited = new boolean[nodes.length];
		int start=0;
		visited[0]=true;
		Comparator<Node> comparator = new NodeComparator();
		PriorityQueue<Node> heap = new PriorityQueue<Node>(comparator);
		Node n = nodes[0];
		while (n.next!=null){
			heap.add(n.next);
			n = n.next;
		}
		while(!heap.isEmpty()){
			n = heap.poll();
			if(!visited[n.elem]){
				visited[n.elem]=true;
				result = result+n.weight;
				Node curr = nodes[n.elem];
				while (curr!=null){
					heap.add(curr);
					curr = curr.next;
				}
			}else{
				//this part delete the unwanted graph to create a MST
				if(n.next!=null){
					n.next.previous = n.previous;
				}if(n.previous!=null){
					n.previous.next=n.next;
				}
			}
		}
		
		
		return result;
	}
	
	private class Node{
		int elem;
		int weight;
		Node next;
		Node previous;
		
		public Node(int e, int w){
			elem = e;
			weight = w;
			next = null;
			previous = null;
		}
		
		/**
		@Override
		public int compare(Node o1, Node o2) {
			Integer i1 = new Integer(o1.weight);
			Integer i2 = new Integer(o2.weight);
			return i1.compareTo(i2);
		}
		*/
		
		
	}
	
	private class NodeComparator implements Comparator<Node>{
		@Override
		public int compare(Node o1, Node o2) {
			Integer i1 = new Integer(o1.weight);
			Integer i2 = new Integer(o2.weight);
			return i1.compareTo(i2);
		}
	}
	
	
	
	
	
	
	
	
	@Override
	public void addEdge(int v1, int v2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void topologicalSort() {
		// TODO Auto-generated method stub
		
	}
}
