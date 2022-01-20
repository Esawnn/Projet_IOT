package business;

import java.util.ArrayList;
import java.util.List;

import entity.Personne;
import entity.Publisher;

public class PublisherBusiness {

	private List<Personne> personnes = new ArrayList<>();
	private List<Publisher> publishers = new ArrayList<>();

	public PublisherBusiness() {
		
	}

	public List<Publisher> getAll() {
		return publishers;
	}


	public void publish(String id, String data) {
		Publisher publisher = searchPublisher(id);
		if(publisher != null) {
			publisher.setData(data);
		} else {
			Publisher new_publisher = new Publisher();
			new_publisher.setId(id);
			new_publisher.setData(data);
		}
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
