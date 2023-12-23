package common.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.stereotype.Service;

@Service("distanceCalcServ")
public class RoutesDriving_Service implements I_RoutesDriving_Service 
{
	// google api
	public Integer checkDistanceOnRoad(Double latitude, Double longitude,  Double prelatitute, Double prelongitude, String API_KEY, Double dist) 
	{
		String result_in_kms = "";
		Double res = null;
	    
	    	  URL url;
			try {
				url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + latitude + "," + longitude + "&destination=" + prelatitute + "," + prelongitude + "&sensor=false&units=metric&mode=driving"+"&key="+API_KEY);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				 
				conn.setRequestMethod("GET");
		    	  String line, outputString = "";
		    	  BufferedReader reader = new BufferedReader(
		    	  new InputStreamReader(conn.getInputStream()));
		    	  while ((line = reader.readLine()) != null) 
		    	  {
		    	       outputString += line;
		    	  }
		    	  result_in_kms = outputString;
		    	  res = Double.valueOf(result_in_kms);

			} 
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(res > dist)
			{
			return 0;	
			}
			else
			return 1;
	}
	
// other  api
public Integer checkDistancePlain(Double lat1, Double lat2, Double lon1,  Double lon2, Double el1, Double el2, Double dist) 
{

    final int R = 6371; // Radius of the earth
    Double latDistance = Math.toRadians(lat2 - lat1);
    Double lonDistance = Math.toRadians(lon2 - lon1);
    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    Double distance = R * c * 1000; // convert to meters

    Double height = el1 - el2;

    distance = Math.pow(distance, 2) + Math.pow(height, 2);

    if(Math.sqrt(distance) > dist)
	{
	return 0;	
	}
	else
	return 1;    
}
}    	  	
