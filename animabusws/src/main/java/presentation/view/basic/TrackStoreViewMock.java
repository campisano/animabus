package presentation.view.basic;

import presentation.view.ITrackStoreView;
import service.track.TrackStoreRequest;
import service.track.TrackStoreResponse;
import common.ICommand;

public class TrackStoreViewMock implements ITrackStoreView {

	private TrackStoreRequest request;
	private ICommand command;
	private String error;
	private TrackStoreResponse response;

	@Override
	public TrackStoreRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(TrackStoreRequest request) {
		this.request = request;
	}

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public String getError() {
		return error;
	}

	@Override
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public TrackStoreResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(TrackStoreResponse response) {
		this.response = response;
	}
}
