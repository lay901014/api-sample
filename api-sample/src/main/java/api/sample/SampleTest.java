package api.sample;

import java.util.HashMap;
import java.util.Map;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SampleTest {

	//api请求地址
	public final static String apiEndPoint = "";
	
	//auth token
	public final static String scope = "exchange_data";
	
	//auth client id
	public final static String clientId = "";
	
	//auth secret
	public final static String secret = "";
	
	//查询语句事例
	public static String selectSql = "{ JG_JL(filter : {GH:{eq:\"${GH}\"}}){ GH CRJSZCM QSNY XH CRZW GZNR BZ JZNY SZDW ZMR SCBJ timestamp  } }";
	
	
	public static void main(String[] args) {
		
		String token = token();
		
		selectSql = selectSql.replace("${GH}", "00001");
		Map<String, String> bodyMap = new HashMap<String, String>();
		bodyMap.put("query", selectSql);
		String response = null;
		try {
			response = HttpUtils.get(apiEndPoint, new Gson().toJson(bodyMap), token);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println(response);
	}
	
	/**
	 * 客户端模式
	 * 获取accessToken
	 * @return
	 */
	public static String token() {
		
		OAuthClientRequest oAuthClientRequest;
		
		final Gson gson = new Gson();
		
        try {
            oAuthClientRequest = OAuthClientRequest
                    .tokenLocation("https://jaccount.sjtu.edu.cn/oauth2/token")
                    .setClientId(clientId)
                    .setClientSecret(secret)
                    .setGrantType(GrantType.CLIENT_CREDENTIALS)
                    .setScope(scope)
                    .buildBodyMessage();
            
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthJSONAccessTokenResponse oAuthResp = oAuthClient.accessToken(oAuthClientRequest);
            JsonObject jsonObject = gson.fromJson(oAuthResp.getBody(), JsonObject.class);
            if(jsonObject != null)
                return jsonObject.get("access_token").getAsString();
            
        }catch (Exception e) {
        	
        }
        
        return null;
	}
	
}
