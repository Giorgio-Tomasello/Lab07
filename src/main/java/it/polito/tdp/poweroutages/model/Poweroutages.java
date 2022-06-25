package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Poweroutages {
	
	private int id;
	private Nerc nerc;
	private int customers_affected;
	private LocalDateTime event_began;
	private LocalDateTime event_finished;
	private int anno;
	private double durata;
	
	
	
	public Poweroutages(int id, Nerc nerc, int customers_affected, LocalDateTime event_began,
			LocalDateTime event_finished) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.customers_affected = customers_affected;
		this.event_began = event_began;
		this.event_finished = event_finished;
		
		LocalDateTime tempDateTime = LocalDateTime.from(event_began);
		this.durata = tempDateTime.until(event_finished, ChronoUnit.HOURS); 
		this.anno = this.event_began.getYear();
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poweroutages other = (Poweroutages) obj;
		return id == other.id;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	public int getCustomers_affected() {
		return customers_affected;
	}
	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}
	public LocalDateTime getEvent_began() {
		return event_began;
	}
	public void setEvent_began(LocalDateTime event_began) {
		this.event_began = event_began;
	}
	public LocalDateTime getEvent_finished() {
		return event_finished;
	}
	public void setEvent_finished(LocalDateTime event_finished) {
		this.event_finished = event_finished;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}



	public double getDurata() {
		return durata;
	}



	public void setDurata(double durata) {
		this.durata = durata;
	}



	@Override
	public String toString() {
		return anno + " " + event_began + " " + event_finished + " " + durata + " " + customers_affected;
	}
	
	
	
	

}
