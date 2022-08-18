package api.sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//2019-04-02
public class HttpUtils {
    

    public static String get(String baseUrl, String body, String authorization) throws IOException {
    	
    	InputStream is = null;
    	OutputStream os = null;
    	BufferedReader br = null;
    	try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");//���ò���������json��ʽ
//            connection.setRequestProperty("Authorization", authorization);
            connection.connect();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String temp = null;
                while((temp = br.readLine()) != null) {
                	buffer.append(temp);
                	buffer.append("\r\n");
                }
                return buffer.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			br.close();
		}
    	
        return null;
    }
    
}
