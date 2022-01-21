package entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Publisher implements Serializable {

	private String id;
	private String data;

}