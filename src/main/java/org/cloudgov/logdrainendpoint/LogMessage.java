package org.cloudgov.logdrainendpoint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
public class LogMessage {
	
	@Id 
	@GeneratedValue
	private Long id;
	
	@Lob
	@NonNull
  private String message;

	protected LogMessage() {}

	public LogMessage(String message) {
		this.message = message;
	}

}