package domain.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
	"COLUMNS",
	"DATA"
})
public class GpsBusList {

	@JsonProperty("COLUMNS")
	private List<String> columns = new ArrayList<String>();

	@JsonProperty("DATA")
	private List<List<String>> data = new ArrayList<List<String>>();

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	private List<GpsBus> gpsBusList = new ArrayList<GpsBus>();
	
	@JsonProperty("COLUMNS")
	public List<String> getColumns() {
		return columns;
	}

	@JsonProperty("COLUMNS")
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	@JsonProperty("DATA")
	public List<List<String>> getData() {
		return data;
	}

	@JsonProperty("DATA")
	public void setDATA(List<List<String>> data) {
		this.data = data;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public List<GpsBus> getGpsOnibusList() {
		if(gpsBusList.isEmpty()) {
			convert();
		}
		
		return gpsBusList;
	}

	public void setGpsOnibusList(List<GpsBus> gpsOnibusList) {
		this.gpsBusList = gpsOnibusList;
	}
	
	private void convert() {
		for (List<String> list : data) {
			GpsBus bus = new GpsBus();
			bus.setDataHora(list.get(0));
			bus.setOrdem(list.get(1));
			bus.setLinha(list.get(2));
			bus.setLatitude(Double.parseDouble(list.get(3)));
			bus.setLongitude(Double.parseDouble(list.get(4)));
			bus.setVelocidade(Float.parseFloat(list.get(5)));
			gpsBusList.add(bus);
		}
	}
}
