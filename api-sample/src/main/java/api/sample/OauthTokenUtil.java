/**   
 * @Description:
 * @Package: sjtu.api.common.util 
 * @author: lulei 
 * @date: 2018骞�11鏈�13鏃� 涓婂崍9:07:45 
 */
package api.sample;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;

import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;



/**   
 * @ClassName:  OauthTokenUtil   
 * @Description:TODO
 * @author: lulei
 * @date:   2018骞�11鏈�13鏃� 涓婂崍9:07:45   
 *     
 */
public class OauthTokenUtil {
    
    public final static Gson gson = new Gson();
    
    public final static String GRAPHL_API_SCOPE = "exchange_data";
    
    public final static String TOKEN_ENDPOINT_URL = "https://jaccount.sjtu.edu.cn/oauth2/token";

    public static String getTokenByClientGrant(String clientId, String secret,
            String scope) {
        
        OAuthClientRequest oAuthClientRequest;
        try {
            oAuthClientRequest = OAuthClientRequest
                    .tokenLocation(TOKEN_ENDPOINT_URL)
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
            return null;
            
        }catch (Exception e) {
            throw new RuntimeException("ClientGrant---鑾峰彇token寮傚父", e);
        }
    }
        
}
