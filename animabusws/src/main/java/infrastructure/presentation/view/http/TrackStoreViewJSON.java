package infrastructure.presentation.view.http;

import infrastructure.presentation.view.http.util.OperationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import presentation.presenter.TrackStorePresenter;
import presentation.view.ITrackStoreView;
import presentation.view.basic.TrackStoreViewMock;
import service.track.TrackService;
import service.track.TrackStoreRequest;
import service.track.TrackStoreResponse;

@Component
@RequestMapping("/track")
public class TrackStoreViewJSON {
	@Autowired
	private TrackService computerService;

	@RequestMapping(value = "/store", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public OperationResponse<TrackStoreResponse> store(
			@RequestBody TrackStoreRequest request) throws Exception {

		OperationResponse<TrackStoreResponse> response = new OperationResponse<TrackStoreResponse>();

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			ITrackStoreView view = new TrackStoreViewMock();

			view.setRequest(request);
			new TrackStorePresenter(view, computerService);
			view.getCommand().execute();

			if (view.getError() != null) {
				response.setError(view.getError());
			} else {
				response.setResponse(view.getResponse());
			}
		} catch (Exception ex) {
			response.setError("Unexpected error:\n" + ex.getMessage());
		}

		return response;
	}
}
