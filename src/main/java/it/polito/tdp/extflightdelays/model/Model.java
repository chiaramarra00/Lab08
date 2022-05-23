package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private List<Airport> airports;
	Map<Integer, Airport> airportsIdMap;

	private Graph<Airport, DefaultWeightedEdge> graph;

	private List<Route> routes = new ArrayList<Route>();

	public List<Airport> getAirports() {
		if (airports == null) {
			ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
			airports = dao.loadAllAirports();

			airportsIdMap = new HashMap<Integer, Airport>();
			for (Airport a : airports)
				airportsIdMap.put(a.getId(), a);

		}
		return airports;
	}

	public void createGraph(int distanzaMinima) {
		graph = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		Graphs.addAllVertices(graph, getAirports());

		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();

		List<Route> airportsToConnect = dao.getAllConnectedAirports();
		for (Route r : airportsToConnect) {
			if (r.getDistance() > distanzaMinima) {
				Graphs.addEdge(graph, airportsIdMap.get(r.getId1()), airportsIdMap.get(r.getId2()), r.getDistance());
				routes.add(new Route(r.getId1(), r.getId2(), r.getDistance()));
			}
		}
	}

	public int nVertex() {
		return graph.vertexSet().size();
	}

	public int nEdge() {
		return graph.edgeSet().size();
	}

	public List<Route> getRoutes() {
		return routes;
	}

}
