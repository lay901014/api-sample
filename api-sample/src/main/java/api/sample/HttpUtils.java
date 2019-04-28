package api.sample;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


//2019-04-28
public class HttpUtils {
    
    
    /**
     * http post(短信验证专用)
     * 
     * @return
     */
    public static String post(String baseUrl, String body, String authorization) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpPost httppost = new HttpPost(baseUrl);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-Type", "application/json;charset=utf-8");
            httppost.setHeader("Authorization", authorization);
            BasicHttpEntity httpEntity = new BasicHttpEntity();
            httpEntity.setContent(new java.io.ByteArrayInputStream(body.getBytes("UTF-8")));
            httpEntity.setContentLength(body.getBytes("UTF-8").length);
            httppost.setEntity(httpEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
