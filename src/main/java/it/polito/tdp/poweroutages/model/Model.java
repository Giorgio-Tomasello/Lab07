package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Poweroutages> best;
	//List<Poweroutages> parziale;
	private int maxPersone;
	private List<Poweroutages> eventi;
	private final static int ANNO_INIZIO=2000; 
	private final static int ANNO_FINALE=2014; 
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public void getPoweroutages(Nerc n) {
		
		this.eventi = podao.getPowerOutages(n);
		
	}
	
	
	
		
	public List<Poweroutages> getWorstCase(int ore, int anni, Nerc nerc){
		
		this.maxPersone=0;
		best = new ArrayList<Poweroutages>();
		//int livello=anni+ANNO_INIZIO;
		
		List<Poweroutages> parziale= new ArrayList<Poweroutages>();
		

		recursive(parziale, ore, anni);  
		
		return best;
	}

	private void recursive(List<Poweroutages> parziale, int ore, int anni) {
	
	
	int somma = somma_persone_affected(parziale);
	
	if(somma > maxPersone)
		{
	
		this.best=new ArrayList<Poweroutages>(parziale);
		maxPersone=somma;
		}
	
			for (Poweroutages p : eventi) {
				if(!parziale.contains(p)) {
					
					parziale.add(p);
					
					
					if(maxOre(ore,parziale)==true && maxAnni(anni,parziale)==true) {
						
						
						recursive(parziale, ore, anni);
						
					}
					parziale.remove(p);
					
					
				}
			
		}
		
			
		
	}
	 

	private boolean maxOre(int ore, List<Poweroutages> parziale) {
		int d=0;
		
		for(Poweroutages p: parziale) {
			d+=p.getDurata();
		}
		if(d<=(ore)) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	private boolean maxAnni(int anni, List<Poweroutages> parziale) {
		double da=0;
		
		if((parziale.size()-1)>1) {
			
			da = parziale.get(parziale.size()-1).getAnno() - parziale.get(0).getAnno();	
		
		if((da)>anni) {
			return false;
		}}
		
		return true;
	}

	private int somma_persone_affected(List<Poweroutages> parziale) {
		
		int persone=0;
		
		for(Poweroutages p:parziale) {
			persone+=p.getCustomers_affected();
		}
		// TODO Auto-generated method stub
		return persone;
	}

}
