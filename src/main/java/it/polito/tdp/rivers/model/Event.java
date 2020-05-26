package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{
	
	private LocalDate day;
	private double fIn;
	private double fOut;

	public Event(LocalDate day, double fIn, double fOut) {
		this.day = day;
		this.fIn = fIn;
		this.fOut = fOut;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public double getfIn() {
		return fIn;
	}

	public void setfIn(double fIn) {
		this.fIn = fIn;
	}

	public double getfOut() {
		return fOut;
	}

	public void setfOut(double fOut) {
		this.fOut = fOut;
	}

	@Override
	public int compareTo(Event o) {
		return this.day.compareTo(o.getDay());
	}
	
	

}
