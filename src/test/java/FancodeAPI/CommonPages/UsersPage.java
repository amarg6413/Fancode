package FancodeAPI.CommonPages;

import UtilsAPI.fancode.HitAPI;
import UtilsAPI.fancode.responseDTO.User;
import dataProvider.ParserClass;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsersPage extends HitAPI {


    public Set<Integer> getUserToCityMap(Set<Integer> userID,String city) throws IOException {

        Response response=getUsersData(userID);
        List<User> users = ParserClass.parseJsonArray(response.asString(), User.class);

        Double latitude =null;
        Double lonitude =null;
        double latMin= Double.parseDouble(properties.getProperty(city + "LatMin"));
        double latMax= Double.parseDouble(properties.getProperty(city + "LatMax"));
        double longMin= Double.parseDouble(properties.getProperty(city + "LongMin"));
        double longMax= Double.parseDouble(properties.getProperty(city + "LongMax"));

        Set<Integer> userIDs=new HashSet<>();
        for (User user : users) {
            latitude= Double.parseDouble(user.getAddress().getGeo().getLat());
            lonitude= Double.parseDouble(user.getAddress().getGeo().getLng());
            if(latitude>=latMin && latitude<=latMax && lonitude>=longMin && lonitude<=longMax){
                userIDs.add(user.getId());
            }
        }
        return userIDs;
    }
}
