package infrastructure.presentation.view.http;

import infrastructure.presentation.view.http.util.OperationResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/")
public class TestViewJSON {
	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public OperationResponse<String> test(@RequestBody String request) {

		System.out.println(request);

		return new OperationResponse<String>("Ok");
	}
}
