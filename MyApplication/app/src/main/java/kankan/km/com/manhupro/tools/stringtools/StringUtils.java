package kankan.km.com.manhupro.tools.stringtools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.security.Timestamp;

import kankan.km.com.manhupro.tools.httptools.TimestampDeserializer;

/**
 * Created by apple on 16/2/18.
 */
public class StringUtils {


    public static Object jsonToBean(Class clazz, String json) {
        try {

            GsonBuilder gsonBuilder = new GsonBuilder();

            gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampDeserializer());

            Gson gson = gsonBuilder.create();

            return gson.fromJson(json, clazz);
        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }

}
