

package common.api;


public interface I_RoutesDriving_Service 
{

public Integer checkDistanceOnRoad(Double latitude, Double longitude,  Double prelatitute, Double prelongitude, String API_KEY, Double dist);
public Integer checkDistancePlain(Double lat1, Double lat2, Double lon1, Double lon2, Double el1, Double el2, Double dist);

}