package infrastructure.presentation.view.http.util;

public class OperationResponse<T> {
	private String error;
	private T response;

	public OperationResponse() {
	}

	public OperationResponse(T response) {
		setResponse(response);
	}

	public OperationResponse(T response, String error) {
		setError(error);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
		this.response = null;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
		this.error = null;
	}
}
