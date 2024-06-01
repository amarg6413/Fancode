package dataProvider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class ParserClass {
    public static <T> T parseJsonResponse(String jsonData, Class<T> responseType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            T response = objectMapper.readValue(jsonData, responseType);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> parseJsonArray(String jsonArray, Class<T> elementType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
    }
}
