package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.omg.CORBA.FREE_MEM;

import it.polito.tdp.crimes.db.EventsDao;




public class Model {
	
	Graph<String, DefaultWeightedEdge> graph;
	EventsDao dao;
	List<Event> eventiDiAnno;
	List<String> vertici;
	List<Integer> anni;
	List<String> reati;

	
public Model() {
		
		this.graph= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.dao = new EventsDao();
		this.eventiDiAnno=new ArrayList<>();
		this.vertici= new ArrayList<>();
		this.anni= new ArrayList<>(dao.listAnni());
		this.reati= new ArrayList<>(dao.listReati());


	}


public List<Integer> getAnni() {
	return anni;
}


public List<String> getReati() {
	return reati;
}


public void creaGrafo(Integer anno, String categoriaR) {
	
	this.vertici=dao.listTipoReati(anno, categoriaR);
	Graphs.addAllVertices(graph, vertici);
	System.out.println(vertici.size());
	
	
	for (String v1 : this.vertici) {
		for (String v2 : this.vertici) {
			if(!v1.equals(v2)){ 
				if(this.graph.getEdge(v1, v2)==null){
				
			int distr1=dao.numDistretti(anno, categoriaR, v1);
			int distr2=dao.numDistretti(anno, categoriaR, v2);
			
			if(distr1==distr2) {
				Graphs.addEdge(this.graph, v1, v2, distr1);
			
			}
			}
			
			
			}
		}
		
	}
	

	System.out.println("archi creati: " + this.graph.edgeSet().size());
	
}

public List<CoppieReati> trovaCoppieMax(){
	List<CoppieReati> resCoppieReatis = new ArrayList<CoppieReati>();
	Double count= 0.0;
	for (DefaultWeightedEdge edge : this.graph.edgeSet()) {
		
		if(graph.getEdgeWeight(edge)>=count) {
			resCoppieReatis.add(new CoppieReati(this.graph.getEdgeSource(edge),	this.graph.getEdgeTarget(edge)));
			count = graph.getEdgeWeight(edge);
			
			
		}
		
	}
	return resCoppieReatis;
	
}

	


}
