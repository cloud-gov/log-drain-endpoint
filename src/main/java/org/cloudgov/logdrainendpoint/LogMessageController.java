package org.cloudgov.logdrainendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LogMessageController {
	
	private final LogMessageRepository repository;

	@Autowired
	public LogMessageController(final LogMessageRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/")
	public ResponseEntity<String> log(@RequestBody final String body) throws Exception {
		System.out.println("BODY: " + body);
		addMessage(body);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private void addMessage(final String msg) {
		LogMessage logMessage = SpammerMessageParser.parse(msg);
		if ( logMessage != null ) {
			repository.save(logMessage);
		}
	}
}