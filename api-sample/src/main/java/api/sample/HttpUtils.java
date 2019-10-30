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
    
    
    /**
     *
     * http post(短信验证专用)
     * 
     * @return
     * @throws IOException 
     */
    public static String post(String baseUrl, String body, String authorization) throws IOException {
    	
    	/**
    	 * ʹ��httpClient����api�ӿڣ����ͨ��ѭ����ʽ�������ýӿڣ����ܻᱨconnection reset���⡣
    	 * ����������ԭ����ҪHTTP_1.1������keep-alivedģʽ��һ��socket���ӿ��Ա��ֳ����ӡ���������tomcat��Ӧkeep-alivedӵ��ʱЧ�Ի��ƣ�����һ��ʱ��Ὣconnection close
    	 * ����client����api���󣬷��������Ѿ��Ͽ�
    	 * ���ʹ��HTTPURLConnection��Ϊ����
    	 */
//        try {
//            CloseableHttpClient httpclient = HttpClients.createDefault();
//
//            HttpPost httppost = new HttpPost(baseUrl);
//            httppost.setHeader("Accept", "application/json");
//            httppost.setHeader("Content-Type", "application/json;charset=utf-8");
//            httppost.setHeader("Authorization", authorization);
//            BasicHttpEntity httpEntity = new BasicHttpEntity();
//            httpEntity.setContent(new java.io.ByteArrayInputStream(body.getBytes("UTF-8")));
//            httpEntity.setContentLength(body.getBytes("UTF-8").length);
//            httppost.setEntity(httpEntity);
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                // 获取响应实体
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    return EntityUtils.toString(entity);
//                }
//            } finally {
//                response.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	InputStream is = null;
    	OutputStream os = null;
    	BufferedReader br = null;
    	try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST"); 
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");//���ò���������json��ʽ
            connection.setRequestProperty("Authorization", authorization);
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
