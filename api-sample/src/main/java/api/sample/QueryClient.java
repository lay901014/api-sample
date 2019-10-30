package api.sample;

import java.util.List;

public interface QueryClient {

	public QueryResponse queryData(String graphqlStatement, String clientToken);
	
	public <T> List<T> queryData(String graphqlStatement, String typeName, 
	        Class<T> resultCls, String clientToken);
}
