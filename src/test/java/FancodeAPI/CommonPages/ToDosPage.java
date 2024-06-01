package FancodeAPI.CommonPages;

import UtilsAPI.fancode.HitAPI;
import UtilsAPI.fancode.responseDTO.ToDo;
import dataProvider.ParserClass;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ToDosPage extends HitAPI {

    public Set<Integer> getUserIDHavingTodos() throws IOException {

        Response response=getToDoList();
        List<ToDo> todos= ParserClass.parseJsonArray(response.asString().trim(),ToDo.class);

        assert todos != null;
        return todos.stream().map(ToDo::getUserId).collect(Collectors.toSet());
    }

    public int getTotalTodoForUser(int userID) throws IOException {
        Response response=getToDoList(userID);
        List<ToDo> todos= ParserClass.parseJsonArray(response.asString().trim(),ToDo.class);
        return todos.size();
    }

    public int getCompletedTodoForUser(int userID) throws IOException {
        Response response=getToDoList(userID);
        List<ToDo> todos= ParserClass.parseJsonArray(response.asString().trim(),ToDo.class);
        return (int) todos.stream().filter(ToDo::isCompleted).count();
    }
}
