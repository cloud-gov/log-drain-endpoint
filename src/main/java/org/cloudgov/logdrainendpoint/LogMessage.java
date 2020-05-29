package org.cloudgov.logdrainendpoint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	private long messageNumber;

	@NonNull
	private String appInstanceGuid;

	protected LogMessage() {}

	public LogMessage(long messageNumber, String appInstanceGuid) {
		this.messageNumber = messageNumber;
		this.appInstanceGuid = appInstanceGuid;
	}

}