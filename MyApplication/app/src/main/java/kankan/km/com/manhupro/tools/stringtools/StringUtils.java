package kankan.km.com.manhupro.tools.stringtools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.security.MessageDigest;
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

    public static String stringToMD5(String plainText){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());

            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            return buf.toString();

        } catch (Exception e){


        }

        return "";

    }

}
