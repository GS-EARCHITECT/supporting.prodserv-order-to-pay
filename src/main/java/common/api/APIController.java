package common.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class APIController 
{
	//private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	private I_RoutesDriving_Service distanceCalcServ;

	@GetMapping(value = "/checkDistanceFromGoogle/{latitude}/{longitude}/{prelatitute}/{prelongitude}/{dist}")
	public ResponseEntity<Integer> getDistanceOnRoad(@PathVariable Double latitude, @PathVariable Double longitude,
			@PathVariable Double prelatitute, @PathVariable Double prelongitude, @RequestBody String API_KEY, @PathVariable Double dist) {
		HttpHeaders httpHeaders = new HttpHeaders();
		Integer result = distanceCalcServ.checkDistanceOnRoad(latitude, longitude, prelatitute, prelongitude, API_KEY, dist);
		return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping(value = "/checkDistancePlain/{latitude}/{longitude}/{prelatitude}/{prelongitude}/{el1}/{el2}/{dist}")
	public ResponseEntity<Integer> getDistancePlain(@PathVariable Double latitude, @PathVariable Double longitude,
			@PathVariable Double prelatitude, @PathVariable Double prelongitude, @PathVariable Double el1,
			@PathVariable Double el2, @PathVariable Double dist) {
		HttpHeaders httpHeaders = new HttpHeaders();
		Integer result = distanceCalcServ.checkDistancePlain(latitude, prelatitude, longitude, prelongitude, el1, el2, dist);
		return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED);
	}

}
