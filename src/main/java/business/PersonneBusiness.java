package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Personne;
import entity.Publisher;

public class PersonneBusiness {

	private List<Personne> personnes = new ArrayList<>();
	private List<Publisher> publishers = new ArrayList<>();


	public PersonneBusiness() {
		
	}
	
	
	public List<Personne> getAll() {
		return personnes;
	}
	
	public String getAllDataPersonne(List<Publisher> mesPublishers) {
		
		String data = "";
		
		for(int i = 0;i<mesPublishers.size();i++ ) {
			data += "{" +mesPublishers.get(i).getData()+"|";
		}
		return data; 
	}


	public List<Publisher> subscribe(String[] tabPublisher, String TOKEN) {
		Personne personne = searchPersonne(TOKEN);
		List<Publisher> mesPublishers = new ArrayList();
		if(personne != null) {
			for(int i =0;i<tabPublisher.length;i++) {
				Publisher publisher = searchPublisher(tabPublisher[i]);
				if(publisher != null) {
					mesPublishers.add(publisher);
				}
			}
			personne.setMesPublishers(mesPublishers);
		}
		return mesPublishers;
	}


	public Personne searchPersonne(String TOKEN) {
		if (TOKEN != null) {
			return personnes.stream().filter(p -> TOKEN.contains(p.getToken())).findAny()
					.orElse(null);
		}
		return null;
	}
	
	public Publisher searchPublisher(String id) {
		if (id != null) {
			return publishers.stream().filter(p -> id.contains(p.getId())).findAny()
					.orElse(null);
		}
		return null;
	}
	
	
	
	
	

}
