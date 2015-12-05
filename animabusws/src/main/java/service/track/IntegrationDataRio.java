package service.track;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import domain.entity.GpsBusList;

@Service
public class IntegrationDataRio {

	public GpsBusList getListBus() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GpsBusList> response = restTemplate.getForEntity(
				"http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes",
				GpsBusList.class);

		GpsBusList gpsBusList = response.getBody();
		
		return gpsBusList;
	}

}
