package service.track;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.entity.GpsBus;
import domain.entity.GpsBusFront;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
public class GpsBusServiceTest {

	@Autowired
	GpsBusService gpsBusService;
	
	@Test
	public void test() throws ParseException {
		List<GpsBusFront> gpsBusFrontList = new ArrayList<GpsBusFront>();

		GpsBusFront gpsBusFront1 = new GpsBusFront();
		GpsBusFront gpsBusFront2 = new GpsBusFront();
		GpsBusFront gpsBusFront3 = new GpsBusFront();
		GpsBusFront gpsBusFront4 = new GpsBusFront();
		
		gpsBusFront1.setTimestamp("02-27-2016 01:40:17");
		gpsBusFront1.setLatitude("-22.8762");
		gpsBusFront1.setLongitude("-43.32999");

		gpsBusFront2.setTimestamp("02-27-2016 01:22:48");
		gpsBusFront2.setLatitude("-22.876511");
		gpsBusFront2.setLongitude("-43.330318");
		
		gpsBusFront3.setTimestamp("02-27-2016 01:22:53");
		gpsBusFront3.setLatitude("-22.87676");
		gpsBusFront3.setLongitude("-43.330212");
		
		gpsBusFront4.setTimestamp("02-27-2016 01:25:23");
		gpsBusFront4.setLatitude("-22.87674");
		gpsBusFront4.setLongitude("-43.329689");
		
		gpsBusFrontList.add(gpsBusFront1);
//		gpsBusFrontList.add(gpsBusFront2);
//		gpsBusFrontList.add(gpsBusFront3);
//		gpsBusFrontList.add(gpsBusFront4);
		
		List<GpsBus> busIntersectionList = new ArrayList<GpsBus>();
		
		busIntersectionList = gpsBusService.findBus("457", gpsBusFrontList, busIntersectionList);
		
		gpsBusFrontList.remove(gpsBusFront1);
		gpsBusFrontList.add(gpsBusFront2);
		
		busIntersectionList = gpsBusService.findBus("457", gpsBusFrontList, busIntersectionList);
		
		for (GpsBus gpsBus : busIntersectionList) {
			System.out.println(gpsBus.getLinha() + " : " + gpsBus.getOrdem());
		}
	}

}
