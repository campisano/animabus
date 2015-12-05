package service.track;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.entity.GpsBus;
import domain.entity.GpsBusList;
import domain.repository.GpsBusRepository;

@Service
public class GpsBusService {

	@Autowired
	GpsBusRepository gpsBusRepository;
	
	@Autowired
	IntegrationDataRio integrationDataRio;
	
	public void insert(GpsBus bus) {
		gpsBusRepository.insert(bus);
	}
	
	public void insertListBus() {
		GpsBusList gpsBusList = integrationDataRio.getListBus();
		
		for (GpsBus bus : gpsBusList.getGpsOnibusList()) {
			bus.setRegistro(new Date());

			insert(bus);
		}
	}
	
}
