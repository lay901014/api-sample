package api.sample;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 閫氳繃鍙嶅皠  瀹炵幇娉涘瀷绫诲瀷鍙傛暟鍖� 
 * 鏋勯�犱竴涓狶ist<T>, T鍏蜂綋鍙傛暟鍖�, 鏂逛究gson姝ｇ‘瑙ｆ瀽
 * @ClassName:  ListParameterizedTypeImpl   
 * @Description:TODO
 * @author: lulei
 * @date:   2018骞�9鏈�4鏃� 涓嬪崍1:40:19   
 *
 */
@SuppressWarnings("rawtypes")
public class ListParameterizedTypeImpl implements ParameterizedType {

	 
	private Class clazz;
     
	public ListParameterizedTypeImpl(Class clz) {
         clazz = clz;
     }

     @Override
     public Type[] getActualTypeArguments() {
         return new Type[]{clazz};
     }

     @Override
     public Type getRawType() {
         return List.class;
     }

     @Override
     public Type getOwnerType() {
         return null;
     }

}
