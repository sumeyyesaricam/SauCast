package uzem.saucast.helper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import uzem.saucast.model.Webinar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonParser {
	public static <T> List<T> getObject(String result) throws IOException, ClassNotFoundException {
        Type type = new TypeToken<List<T>>() {}.getType();
        Gson gson = new Gson();
        List<T> _res = gson.fromJson(result, type);
        return _res;
    }
	
    public static List<Webinar> getWebinarObject(String result) throws IOException, ClassNotFoundException {
        Type type = new TypeToken<List<Webinar>>(){}.getType();
        Gson gson = new Gson();
        List<Webinar> _res = gson.fromJson(result, type);
        return _res;
    }
    public static String getStringObject(String result) throws IOException, ClassNotFoundException {
        Type type = new TypeToken<String>() {}.getType();
        Gson gson = new Gson();
        String _res = gson.fromJson(result, type);
        return _res;
    }
}
