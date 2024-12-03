package com.cameodud.graph;

import java.util.Set;

public interface Graph<T> {
	
	// Add a non-connected node
	public void addVertex(T parent);

    // Add an edge between two nodes of type T
    public void addEdge(T from, T to);

    // Remove an edge between two nodes of type T
    public void removeEdge(T from, T to);
    
    // Remove a node from the graph
    public void removeVertex(T node);

    // Check if two nodes are connected
    public boolean isConnected(T from, T to);

    // Get neighbors of a given node
    public Set<T> getNeighbors(T node);

    // Get the number of nodes in the graph
    public int nodeCount();

    // Get the number of edges in the graph
    public int edgeCount();

    // Check if the graph contains a specific node
    public boolean containsNode(T node);

    // Check if there is an edge between two nodes
    public boolean containsEdge(T from, T to);

    // Clear all nodes and edges from the graph
    public void clear();

    // Check if the graph is directed
    public boolean isDirected();

    // Traverse the graph using a specific traversal strategy (DFS or BFS)
    public String traverse(TraversalType type);

    // Enum to represent traversal types
    public enum TraversalType {
        DFS,
        BFS
    }
}