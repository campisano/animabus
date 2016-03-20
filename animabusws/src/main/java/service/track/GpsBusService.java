package service.track;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	private void insert(GpsBus bus) {
		gpsBusRepository.insert(bus);
	}

	public void insertListBus() throws ParseException {
		GpsBusList gpsBusList = integrationDataRio.getListAllBus();

		for (GpsBus bus : gpsBusList.getGpsBusList()) {
			bus.setRegistro(new Date());

			insert(bus);
		}
	}

	public List<GpsBus> findBus(String busLine, List<GpsBusFront> gpsBusFrontList, 
			List<GpsBus> busIntersectionList) throws ParseException {
		List<GpsBus> targetBusList = new ArrayList<GpsBus>();

		for (GpsBusFront gpsBusFront : gpsBusFrontList) {
			targetBusList.addAll(findTargetBus(busLine, gpsBusFront.getTimestamp(), 5, 
					gpsBusFront.getLatitude(), gpsBusFront.getLongitude(), 0.0002d));
		}

		if(!targetBusList.isEmpty()) {
			if(busIntersectionList == null || busIntersectionList.isEmpty()) {
				busIntersectionList = targetBusList;
			} else {
				busIntersectionList.retainAll(targetBusList);
			}
		}

		return busIntersectionList;
	}

	private List<GpsBus> findTargetBus(String busLine, Date initialTime, int interval, Double latitude, 
			Double longitude, double raio) throws ParseException {
		List<GpsBus> targetBusList = new ArrayList<GpsBus>();

		busLine = busLine.replace(".0", "");
		GpsBusList gpsBusList = integrationDataRio.getListBus(busLine);

		for (GpsBus bus : gpsBusList.getGpsBusList()) {
			bus.setRegistro(new Date());

			//TODO acertar medição, pois a latitude está em graus e o raio em metros
			if(bus.getLatitude() <= (latitude + raio) && bus.getLatitude() >= (latitude - raio)) {
				if(bus.getLongitude() <= (longitude + raio) && bus.getLongitude() >= (longitude - raio)) {

					//TODO melhorar lógica da data
					Calendar initialTimeMin = Calendar.getInstance();
					initialTimeMin.setTime(initialTime);
					initialTimeMin.set(Calendar.MINUTE, initialTimeMin.get(Calendar.MINUTE) - interval);

					Calendar initialTimeMax = Calendar.getInstance();
					initialTimeMax.setTime(initialTime);
					initialTimeMax.set(Calendar.MINUTE, initialTimeMax.get(Calendar.MINUTE) + interval);

					if(bus.getDataHora().after(initialTimeMin.getTime()) && 
							bus.getDataHora().before(initialTimeMax.getTime())) {
						targetBusList.add(bus);
					}
				}
			}
		}

		return targetBusList;
	}

}
