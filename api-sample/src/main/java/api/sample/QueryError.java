package api.sample;

import java.io.Serializable;

public class QueryError implements Serializable{

	private static final long serialVersionUID = 8052087063687446019L;

	private String message;
	
	private String description;
	
	private String errorType;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	
}
