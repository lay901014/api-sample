package api.sample;

import java.io.File;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;

public class PicTest {

	public static void main(String[] args) {
		QueryClient queryClient = new GraphqlQueryClient("http://graphql.sjtu.edu.cn/data-api-web/graphql");
	    QueryResponse queryResponse = queryClient.queryData("{ TRA_PIC_NEW(first : 1,offset : 100){ PIC} }", 
	            OauthTokenHelper.getAccessToken("${clientId}", "${secret}"));
	    List<Pic> list = queryResponse.getQueryData("TRA_PIC_NEW", Pic.class);
	    for(int i=0; i<list.size(); i++) {
	        try{
	           Pic pic = list.get(i);
	           if(pic.getPIC() != null) {
	               FileImageOutputStream imageOutput = new FileImageOutputStream(new File("D:/".concat(Integer.toString(i)).concat(".jpg")));
	               imageOutput.write(pic.getPIC(), 0, pic.getPIC().length);
	               imageOutput.close();
	               System.out.println("Make Picture success,Please find image in " );
	           }
	            
	        } catch(Exception ex) {
	            System.out.println("Exception: " + ex);
	            ex.printStackTrace();
	        }
	    }
	    System.out.println("ok");
	}
}
