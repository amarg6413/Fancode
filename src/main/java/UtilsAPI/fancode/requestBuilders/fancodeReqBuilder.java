package UtilsAPI.fancode.requestBuilders;

import io.restassured.builder.RequestSpecBuilder;
import utils.ApiUtils;

public class fancodeReqBuilder extends ApiUtils {

    public RequestSpecBuilder getFancodeBaseBuilder(){
        return getBaseReqBuilder(properties.getProperty("fancode.base.uri"));
    }

}
