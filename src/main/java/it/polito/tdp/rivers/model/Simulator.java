package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulator {
	
	private PriorityQueue<Event> queue;
	private List<Flow> flows;
	private double avg;
	private double k;
	private double Q;
	private double C;
	private double fOutMin;
	private double fOutMax;
	private Integer numEvents;
	private double sommaC;
	private Integer numGiorni;
	
	private final double P = 5;
	
	public Simulator() {
		
	}
	
	public void inizializza(List<Flow> flows, double avg, double k) {
		this.flows = flows;
		this.avg = avg;
		this.k = k;
		
		this.Q = k*avg*30;
		this.C = Q/2;
		this.fOutMin = 0.8*avg;
		this.fOutMax = 10*fOutMin;
		this.numEvents = 0;
		this.sommaC = 0;
		this.numGiorni = 0;
	}
	
	public void generaEventi() {
		Random random = new Random();
		Event e = null;
		
		for(Flow f : flows) {
			if(random.nextInt(100)+1<=P) {
				e = new Event(f.getDay(), f.getFlow(), fOutMax);
				queue.add(e);
			}
			else {
				e = new Event(f.getDay(), f.getFlow(), fOutMin);
				queue.add(e);
			}
		}
		
		this.numEvents = queue.size();
	}
	
	public void run() {
		this.queue = new PriorityQueue<>();
		this.generaEventi();
		
		while(!queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}		
	}

	private void processEvent(Event e) {
		C += e.getfIn();
		C -= e.getfOut();
		
		if(C>Q)
			C = Q;
	
		if(C<0) {
			C = 0;
			numGiorni++;
		}
		
		sommaC += C;
	}
	
	public Integer getNumGiorni() {
		return numGiorni;
	}
	
	public double getMediaC() {
		return (sommaC/numEvents)*86400;
	}
}
