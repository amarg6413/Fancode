package UtilsAPI.fancode;

import UtilsAPI.fancode.requestBuilders.fancodeReqBuilder;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Set;


public class HitAPI extends fancodeReqBuilder {

    public Response getToDoList(){
        return hitGETAPI(getFancodeBaseBuilder().build(), FancodeEndPoints.TODOs.getEndPoint());
    }

    public Response getUsersData(Set<Integer> userId){
        Map queryParam = Map.of("id",userId);
        return hitGETAPI(getFancodeBaseBuilder().build(), FancodeEndPoints.USERS.getEndPoint(),queryParam);
    }

    public Response getToDoList(int userId){
        Map queryParam = Map.of("userId",userId);
        return hitGETAPI(getFancodeBaseBuilder().build(), FancodeEndPoints.TODOs.getEndPoint(),queryParam);
    }
}
