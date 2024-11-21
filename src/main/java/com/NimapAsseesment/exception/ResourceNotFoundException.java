package com.NimapAsseesment.exception;

import java.util.Date;

public class ResourceNotFoundException extends RuntimeException{
	 private static final long serialVersionUID = 1L;

	
	    
	    private Date timestamp;
	    private String message;
	    private String details;

	    public ResourceNotFoundException(String message, String details) {
	        super(message);
	        this.timestamp = new Date();
	        this.message = message;
	        this.details = details;
	    }

	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public String getDetails() {
	        return details;
	    }

}
