package it.polito.tdp.extflightdelays.model;

import java.util.Map;

public class Route {
	
	private int id1;
	private int id2;
	private double distance;

	public Route(int id1, int id2, double distance) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.distance = distance;
	}
	
	public int getId1() {
		return id1;
	}

	public int getId2() {
		return id2;
	}

	public double getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return id1 + " - " + id2 + " (distance = " + distance + ")";
	}
	

}
