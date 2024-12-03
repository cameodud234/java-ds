package com.cameodud.main;

import com.cameodud.graph.AGraph;
import com.cameodud.graph.Graph;

public class Main {
	public static void main(String[] args) {
		AGraph<String> g = new AGraph<>();
		g.addEdge("Cameron", "Kirin");
		g.addEdge("Kirin", "Mom");
		g.addEdge("Cameron", "Dad");
		System.out.println(g.getRandKey() + ", " + g.traverse(Graph.TraversalType.BFS));
		System.out.println(g.getRandKey() + ", " + g.traverse(Graph.TraversalType.DFS));
	}
}