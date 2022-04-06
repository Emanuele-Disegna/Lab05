package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {
	
	private AnagrammaDAO aDAO;
	private List<String> soluzioni;
	
	public Model() {
		super();
		aDAO = new AnagrammaDAO();
		soluzioni = new ArrayList<String>();
	}

	public Set<String> getDizionario() {
		return aDAO.getDizionario();
	}
	
	public boolean isCorrect(String s) {
		return aDAO.isCorretto(s);
	}
	
	public List<String> anagramma(String s) {
		this.anagrammaRicorsiva("", 0, s);
		return soluzioni;
	}
	
	private void anagrammaRicorsiva(String parziale, int livello, String rimanenti) {
		
		if(rimanenti.length()==0) {
			soluzioni.add(parziale);
		} else { 
			for(int pos=0; pos<rimanenti.length(); pos++) {
				this.anagrammaRicorsiva(parziale+rimanenti.charAt(pos), 
						livello+1, 
						rimanenti.substring(0,pos)+rimanenti.substring(pos+1));
			}
			
			
		}
		
	}
}
