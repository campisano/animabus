package service.track;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.entity.GpsBus;
import domain.entity.GpsBusFront;
import domain.entity.GpsBusList;
import domain.repository.GpsBusRepository;

@Service
public class GpsBusService {

	@Autowired
	GpsBusRepository gpsBusRepository;

	@Autowired
	IntegrationDataRio integrationDataRio;
	
	List<GpsBus> mockGpsBus = makeMock();
	
	private List<GpsBus> makeMock() {
		List<GpsBus> gpsBusList = new ArrayList<GpsBus>();

		GpsBus gpsBusFront1 = new GpsBus();
		GpsBus gpsBusFront2 = new GpsBus();
		GpsBus gpsBusFront3 = new GpsBus();
		GpsBus gpsBusFront4 = new GpsBus();
		
		gpsBusFront1.setLinha("457.0");
		gpsBusFront1.setDataHora("02-27-2016 01:40:17");
		gpsBusFront1.setLatitude("-22.8762");
		gpsBusFront1.setLongitude("-43.32999");
		gpsBusFront1.setOrdem("ABC");

		gpsBusFront2.setLinha("457.0");
		gpsBusFront2.setDataHora("02-27-2016 01:22:48");
		gpsBusFront2.setLatitude("-22.876511");
		gpsBusFront2.setLongitude("-43.330318");
		gpsBusFront2.setOrdem("ABC");
		
		gpsBusFront3.setLinha("457.0");
		gpsBusFront3.setDataHora("02-27-2016 01:22:53");
		gpsBusFront3.setLatitude("-22.87676");
		gpsBusFront3.setLongitude("-43.330212");
		gpsBusFront3.setOrdem("CDB");
		
		gpsBusFront4.setLinha("457.0");
		gpsBusFront4.setDataHora("02-27-2016 01:25:23");
		gpsBusFront4.setLatitude("-22.87674");
		gpsBusFront4.setLongitude("-43.329689");
		gpsBusFront4.setOrdem("ABCDE");
		
		gpsBusList.add(gpsBusFront2);
		gpsBusList.add(gpsBusFront3);
		gpsBusList.add(gpsBusFront4);
		gpsBusList.add(gpsBusFront1);
		
		return gpsBusList;
	}

	private void insert(GpsBus bus) {
		gpsBusRepository.insert(bus);
	}

	public void insertListBus() {
		GpsBusList gpsBusList = integrationDataRio.getListAllBus();

		for (GpsBus bus : gpsBusList.getGpsOnibusList()) {
			bus.setRegistro(new Date());

			insert(bus);
		}
	}

	public List<GpsBus> findBus(String busLine, List<GpsBusFront> gpsBusFrontList, List<GpsBus> busIntersectionList) throws ParseException {
		for (GpsBusFront gpsBusFront : gpsBusFrontList) {
			List<GpsBus> targetBusList = findTargetBus(busLine, gpsBusFront.getTimestamp(), 5, 
					gpsBusFront.getLatitude(), gpsBusFront.getLongitude(), 0.0002f);

			if(!targetBusList.isEmpty()) {
				if(busIntersectionList.isEmpty()) {
					busIntersectionList = targetBusList;
				} else {
					//TODO testar se esse retainAll realmente é o que eu quero
					busIntersectionList.retainAll(targetBusList);
				}
			}
		}

		return busIntersectionList;
	}

	private List<GpsBus> findTargetBus(String busLine, String initialTime, int interval, String latitude, String longitude, 
			float raio) throws ParseException {
		List<GpsBus> targetBusList = new ArrayList<GpsBus>();
		
//		busLine = busLine.replace(".0", "");
//		Integer busLineInteger = Integer.parseInt(busLine);
//		GpsBusList gpsBusList = integrationDataRio.getListBus(busLine);

		Float latitudeFloat = Float.parseFloat(latitude);
		Float longitudeFloat = Float.parseFloat(longitude);

//		for (GpsBus bus : gpsBusList.getGpsOnibusList()) {
		for (GpsBus bus : mockGpsBus) {
			bus.setRegistro(new Date());

			if(bus.getLinha().equals(busLine)) {
				//TODO acertar medição, pois a latitude está em graus e o raio em metros
				if(Float.parseFloat(bus.getLatitude()) <= latitudeFloat + raio 
						&& Float.parseFloat(bus.getLatitude()) >= latitudeFloat - raio) {
					if(Float.parseFloat(bus.getLongitude()) <= longitudeFloat + raio 
							&& Float.parseFloat(bus.getLongitude()) >= longitudeFloat - raio) {

						//TODO melhorar lógica da data
						SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");						
						Date busDate = formatter.parse(bus.getDataHora());
						Date initialTimeDate = formatter.parse(initialTime);

						Calendar initialTimeMin = Calendar.getInstance();
						initialTimeMin.setTime(initialTimeDate);
						initialTimeMin.set(Calendar.MINUTE, initialTimeMin.get(Calendar.MINUTE) - interval);

						Calendar initialTimeMax = Calendar.getInstance();
						initialTimeMax.setTime(initialTimeDate);
						initialTimeMax.set(Calendar.MINUTE, initialTimeMax.get(Calendar.MINUTE) + interval);

						if(busDate.after(initialTimeMin.getTime()) && busDate.before(initialTimeMax.getTime())) {
							targetBusList.add(bus);
						}
					}
				}

			}
		}

		return targetBusList;
	}

}
