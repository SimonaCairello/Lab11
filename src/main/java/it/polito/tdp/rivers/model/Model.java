package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private Simulator sim;
	private List<Flow> flows;
	private double avg;

	public Model() {
		this.dao = new RiversDAO();
		this.sim = new Simulator();
		this.flows = new LinkedList<Flow>();
		this.avg = 0;
	}
	
	public List<River> getAllRivers() {
		return this.dao.getAllRivers();
	}
	
	public void getFlowRiver(River river) {
		this.flows = this.dao.getFlowRiver(river);
	}
	
	public LocalDate getFirstDate() {
		return this.flows.get(0).getDay();
	}
	
	public LocalDate getLastDate() {
		return this.flows.get(flows.size()-1).getDay();
	}
	
	public Integer getNumFlow() {
		return this.flows.size();
	}
	
	public double getFlowAvg(River river) {
		avg = this.dao.getFlowAvg(river);
		return avg;
	}
	
	public void simulaEventi(double k) {
		this.sim.inizializza(this.flows, this.avg, k);
		this.sim.run();
	}
	
	public Integer getNumGiorni() {
		return this.sim.getNumGiorni();
	}
	
	public double getMediaC() {
		return this.sim.getMediaC();
	}
}
