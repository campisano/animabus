package service.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.entity.GpsBusList;
import domain.repository.GpsBusRepository;

@Service
public class GpsBusService {

	@Autowired
	GpsBusRepository gpsBusRepository;
	
	@Autowired
	IntegrationDataRio integrationDataRio;
	
	public void insertListBus() {
		GpsBusList gpsBusList = integrationDataRio.getListBus();
		
		gpsBusRepository.insertList(gpsBusList.getGpsOnibusList());
	}
}
