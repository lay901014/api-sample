package api.sample;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QueryResponse implements Serializable {

	private static final long serialVersionUID = 5683359516021310622L;
	//
	private static final  Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	private Map<String, List<Object>> data;
	
	private List<QueryError> errors;

	public Map<String, List<Object>> getData() {
		return data;
	}

	public void setData(Map<String, List<Object>> data) {
		this.data = data;
	}

	public List<QueryError> getErrors() {
		return errors;
	}

	public void setErrors(List<QueryError> errors) {
		this.errors = errors;
	}
	
	public <T> List<T> getQueryData(String typeName, Class<T> resultCls) {
		if(data.get(typeName) == null)
			return null;
		else {
			List<Object> list = data.get(typeName);
			Type type = new ListParameterizedTypeImpl(resultCls);
			List<T> result = gson.fromJson(gson.toJson(list), type);
        	return result;
		}
	}
	
	public Boolean hasError() {
		return CollectionUtils.isEmpty(errors) ? false : true;
	}

}
