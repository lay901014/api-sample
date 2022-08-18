package api.sample;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;



public class HttpProxyImpl implements HttpProxy{

    private final static Gson gson = new Gson();


    @Override
    public <T> T executePost(String url, String body,
            Class<T> resultClaz, String clientToken) {
        String json;
		try {
			json = HttpUtils.get(url, body);
			T result = parse(json, resultClaz);
	        return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }

    
    
    private <T> T parse(String json, Class<T> resultClazz) {
        if(StringUtils.isEmpty(json))
        	return null;
        else {
        	T result = gson.fromJson(json, resultClazz);
        	return result;
        }
    }


}
