package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Flow {
	private Integer id;
	private LocalDate day;
	private double flow;
	private River river;

	public Flow(Integer id, LocalDate day, double flow, River river) {
		this.id = id;
		this.day = day;
		this.flow = flow;
		this.river = river;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "Flow [id=" + id + ", day=" + day + ", flow=" + flow + ", river=" + river + "]";
	}
	
}
