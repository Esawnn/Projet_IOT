package entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Personne {

	private String token;
	private List<Publisher> mesPublishers;

}
