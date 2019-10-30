package api.sample;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 鑾峰彇token鐨勮緟鍔╃被
 * 缂撳瓨access_token 20鍒嗛挓
 * 鍒╃敤鎳掓眽妯″紡锛岃秴鍑鸿繃鍘绘椂闂达紝閲嶆柊浠巓auth鑾峰彇
 * @ClassName:  OauthTokenHelper   
 * @Description:TODO
 * @author: lulei
 * @date:   2018骞�11鏈�15鏃� 涓婂崍10:17:42   
 *
 */
public class OauthTokenHelper {

    private final static Map<String, TokenInfo> tokenMap = new ConcurrentHashMap<String, TokenInfo>();
    
    private final static Long TOKEN_EXPIRE_TIME = 1200000L;
    
    public synchronized static String getAccessToken(String clientId, String secret) {
        TokenInfo tokenInfo = tokenMap.get(clientId);
        Long now = System.currentTimeMillis();
        if(tokenInfo == null || tokenInfo.getExpireTime() < now) {
            String access_token = token(clientId, secret);
            TokenInfo info = new TokenInfo(access_token, System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
            tokenMap.put(clientId, info);
            return access_token;
        }
        return tokenInfo.getToken();
    }
    
    
    private static String token(String clientId, String secret) {
        
        String access_token = OauthTokenUtil.getTokenByClientGrant(clientId, secret, OauthTokenUtil.GRAPHL_API_SCOPE);
        if(access_token == null) {
            throw new RuntimeException("exception");
        }
        return access_token;
    }
    
    private static class TokenInfo {
        
        private String token;
        
        private Long expireTime;

        public TokenInfo(String token, Long expireTime) {
            this.token = token;
            this.expireTime = expireTime;
        }

        public String getToken() {
            return token;
        }

        public Long getExpireTime() {
            return expireTime;
        }

                
    }
}
