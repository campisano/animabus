package service.track;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import domain.entity.GpsBus;

@JsonInclude(Include.NON_NULL)
public class GpsBusRequest {
	
	private List<GpsBus> busIntersectionList;
	private List<GpsBusFront> gpsBusList;

	public List<GpsBus> getBusIntersectionList() {
		return busIntersectionList;
	}
	public void setBusIntersectionList(List<GpsBus> busIntersectionList) {
		this.busIntersectionList = busIntersectionList;
	}
	public List<GpsBusFront> getGpsBusList() {
		return gpsBusList;
	}
	public void setGpsBusList(List<GpsBusFront> gpsBusList) {
		this.gpsBusList = gpsBusList;
	}
}
