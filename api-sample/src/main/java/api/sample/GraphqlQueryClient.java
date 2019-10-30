package api.sample;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GraphqlQueryClient implements QueryClient {
	
	private final static Logger logger = LoggerFactory.getLogger(GraphqlQueryClient.class);
	
	private final static Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	private String graphqlUrl;
	
	private HttpProxy httpProxy;
	
	public GraphqlQueryClient() {
		httpProxy = new HttpProxyImpl();
	}
	
	public GraphqlQueryClient(String url)  {
		this.graphqlUrl = url;
		httpProxy = new HttpProxyImpl();
	}

	/**
	 * 鏍规嵁graphql鏌ヨ璇彞
	 * @Title: queryData   
	 * @Description: TODO
	 * @param: @param graphqlStatement
	 * @param: @return      
	 * @return: QueryResponse      
	 * @throws
	 */
	@Override
	public QueryResponse queryData(String graphqlStatement, String clientToken) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("query", graphqlStatement);
		QueryResponse response = httpProxy.executePost(graphqlUrl, gson.toJson(map), QueryResponse.class, clientToken);
		return response;
	}
	
	/**
	 * 鑾峰彇key涓篢ypeName鐨勬暟鎹�
	 * @Title: queryData   
	 * @Description: TODO
	 * @param: @param graphqlStatement
	 * @param: @param typeName
	 * @param: @param resultCls
	 * @param: @return      
	 * @return: List<T>      
	 * @throws
	 */
	@Override
	public <T> List<T> queryData(String graphqlStatement, String typeName,
	        Class<T> resultCls, String clientToken) {
		QueryResponse response = queryData(graphqlStatement, clientToken);
		if(response == null || response.getData() == null)
			return null;
		else {
			Map<String, List<Object>> map = response.getData();
			if(map.get(typeName) == null)
				return null;
			else {
				List<Object> data = map.get(typeName);
				Type type = new ListParameterizedTypeImpl(resultCls);
				List<T> result = gson.fromJson(gson.toJson(data), type);
	        	return result;
			}
				
		}
	}
	
	public String getGraphqlUrl() {
		return graphqlUrl;
	}

	public void setGraphqlUrl(String graphqlUrl) {
		this.graphqlUrl = graphqlUrl;
	}
	
	public void urlValidation() {
		if(null == this.graphqlUrl) {
			logger.error("graphqlUrl is null");
			throw new RuntimeException("exception");
		}
		else 
			return;
	}
	

	

}
