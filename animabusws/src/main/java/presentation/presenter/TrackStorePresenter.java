package presentation.presenter;

import presentation.view.ITrackStoreView;
import service.track.TrackService;
import common.ICommand;

public class TrackStorePresenter {

	private ITrackStoreView view;
	private TrackService service;

	public TrackStorePresenter(ITrackStoreView view,
			TrackService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				storeTrack();
			}
		});
	}

	private void storeTrack() {
		try {
			view.setResponse(service.storeTrack(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Register computer error:\n" + ex.getMessage());
		}
	}
}
