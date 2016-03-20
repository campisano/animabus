package infrastructure.presentation.view.http;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.entity.GpsBus;
import service.track.GpsBusRequest;
import service.track.GpsBusService;

@Component
@RequestMapping("/buses")
public class GpsBusController {
	
	@Autowired
	GpsBusService gpsBusService;
	
	@RequestMapping(value = "/{busLine}", method = RequestMethod.POST, consumes = "application/json", 
			produces = "application/json")
	@ResponseBody
	public List<GpsBus> findBus(@PathVariable String busLine, @RequestBody GpsBusRequest request) 
			throws ParseException {
		
		System.out.println("Requisição feita");
		return null; 
//				gpsBusService.findBus(busLine, request.getGpsBusList(), request.getBusIntersectionList());
	}
}
