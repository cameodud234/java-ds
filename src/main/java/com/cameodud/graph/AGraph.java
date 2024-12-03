package com.cameodud.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class AGraph<T> implements Graph<T> {
	
	private static Long seed = 12345L;

	private Map<T, Set<T>> parent_neighbor;
	
	public AGraph() {
		parent_neighbor = new HashMap<T, Set<T>>();
	}
	
	@Override 
	public void addVertex(T parent) { 
		parent_neighbor.computeIfAbsent(parent, k -> new HashSet<>());
	}

	@Override
	public void addEdge(T parent, T neighbor) {
		parent_neighbor.computeIfAbsent(parent, k -> new HashSet<>()).add(neighbor);
		parent_neighbor.computeIfAbsent(neighbor, k -> new HashSet<>()).add(parent);
	}
	
	@Override
	public void removeVertex(T node) { 
		if(parent_neighbor.containsKey(node)) {
			parent_neighbor.remove(node);
			return;
		}
		for(T key: parent_neighbor.keySet()) {
			if(parent_neighbor.get(key).contains(node)) {
				parent_neighbor.get(key).remove(node);
			}
		}
	}

	@Override
	public void removeEdge(T parent, T neighbor) {
		if(parent_neighbor.containsKey(parent)) {
			parent_neighbor.get(parent).remove(neighbor);
		}
	}

	@Override
	public boolean isConnected(T from, T to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<T> getNeighbors(T node) {
		return parent_neighbor.get(node);
	}

	@Override
	public int nodeCount() {
		return parent_neighbor.size();
	}

	@Override
	public int edgeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean containsNode(T node) {
		return parent_neighbor.containsKey(node);
	}

	@Override
	public boolean containsEdge(T from, T to) {
		for(T key: parent_neighbor.keySet()) {
			if(key.equals(from) && parent_neighbor.get(key).equals(to)
					|| key.equals(to) && parent_neighbor.get(to).equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		parent_neighbor.clear();
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String traverse(Graph.TraversalType type) {
		switch (type) {
		case DFS:
			return traverseDFS();
			
		case BFS:
			return traverseBFS();

		default:
			throw new  IllegalArgumentException("Traversal type must be either 'BFS' or 'DFS'");
		}
	}
	
	
	
	private String traverseBFS() {
		StringBuilder sb = new StringBuilder();
		if(nodeCount() == 0) return "";
		Set<T> visited = new HashSet<>();
		Queue<T> q = startQueue();
		while(!q.isEmpty()) {
			T currentNode = q.poll();
			if(!visited.contains(currentNode)) {
				visited.add(currentNode);
			}
		}
		
		return sb.substring(0, sb.length() - 1).toString();
		
	}
	
	private Queue<T> startQueue() {
		
		Queue<T> q = new LinkedList<>();
		
		q.add(getRandKey());

		return q;
		
	}

	private String traverseDFS() {
		StringBuilder sb = new StringBuilder();
		if(nodeCount() == 0) return "";
		Set<T> visited = new HashSet<>();
		Stack<T> s = startStack();
		while(!s.isEmpty()) {
			T currentNode = s.pop();
			if(!visited.contains(currentNode) ) {
				visited.add(currentNode);
				sb.append(currentNode.toString());
				sb.append(",");
				Set<T> neighbors = parent_neighbor.get(currentNode);
				s.addAll(neighbors);
			}
		}
		
		return sb.substring(0, sb.length() - 1).toString();
	}
	
	private Stack<T> startStack() {
		
		Stack<T> s = new Stack<>();
		
		s.add(getRandKey());
		
		return s;
		
	}
	
	public T getRandKey() {
		Random rand = new Random(seed);
		Object[] o = parent_neighbor.keySet().toArray();
		int randomKeyIndex = rand.nextInt(0, parent_neighbor.keySet().toArray().length);
		return (T) o[randomKeyIndex];
	}

}
