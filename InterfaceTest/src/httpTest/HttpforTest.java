package httpTest;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class HttpforTest {
	private final int getT = 0,postT = 1;
	private String strUrl="";
	private CloseableHttpClient httpClient;
	private final int socketT = 20000,connectT = 20000;
	private RequestConfig rConfig = RequestConfig.custom().setSocketTimeout(socketT).setConnectTimeout(connectT).build();
	public HttpforTest() {
		httpClient = HttpClients.createDefault();		
	}
	public HttpforTest setUrl(String strUrl) {
		if (strUrl != null) {
			this.strUrl = strUrl;
			return this;
		}
		return this;
	}
	public HttpforTest setTOut(int socketT,int connectT) {
		rConfig = RequestConfig.custom().setSocketTimeout(socketT).setConnectTimeout(connectT).build();
		return this;
	}
	
	public String get() throws ClientProtocolException, IOException{
		HttpGet httpGet = new HttpGet("http://" + strUrl + "/");
		httpGet.setConfig(rConfig);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		String rteurnStr = getResult(response,getT);
		response.close();
		httpClient.close();
		return rteurnStr;

	}
	public String post(List<NameValuePair> formparams) throws ClientProtocolException, IOException{
		HttpPost httpPost = new HttpPost("http://" + strUrl);
//		httpPost.setHeader(arg0, arg1);
		httpPost.setConfig(rConfig);
		
//		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//			formparams.add(new BasicNameValuePair("username", "three2"));
//			formparams.add(new BasicNameValuePair("password", "123456"));
		
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formparams,"UTF-8");
			
		httpPost.setEntity(formEntity);
		
		CloseableHttpResponse response = httpClient.execute(httpPost);
		
		Header headers[] = response.getAllHeaders();
	    
    	for (Header header : headers) {
    		System.out.println(header.getName()+"----"+header.getValue()+"  ");
		}    	
	    
    	
    	System.out.println(response.getStatusLine().getStatusCode());	
    	
    	
    	String jsonStr = EntityUtils.toString(response.getEntity());	
    	System.out.println(jsonStr);
    	releaseJson(jsonStr);
    	
    	InetAddress[] address = InetAddress.getAllByName("mservice.millionairematch.com");
		  	for (int i = 0; i < address.length; i++) {
		  		System.out.println(address[i]);
		  	}
		  	

		response.close();
		httpClient.close();
		return jsonStr;
		
		
		
	}
	
	private void releaseJson(String jsString) {
		JSONObject jsonObj = JSONObject.fromObject(jsString);  
        Iterator it = jsonObj.keys();  

        while(it.hasNext()){  
        	String key = it.next().toString();
        	if (!JSONUtils.mayBeJSON(jsonObj.get(key).toString())) {
        		System.out.println(key+ ":" + jsonObj.get(key));
			}else {
				System.out.println(key + ":");
				if (!JSONUtils.isArray(jsonObj.get(key))) {
					JSONObject jjsonObj = JSONObject.fromObject(jsonObj.get(key));
					Iterator iit = jjsonObj.keys();
					while (iit.hasNext()) {
						String kkey = iit.next().toString();
						System.out.println("\t" + kkey + ":" + jjsonObj.get(kkey));
					}
				}else {
					System.out.println("-------------------Sorry,I can't deal with it!-------------------");
				}
			}
        }  
		
	}
	
	private String getResult(CloseableHttpResponse response,int type) throws ClientProtocolException, IOException {
		Header headers[] = response.getAllHeaders();
    	for (Header header : headers) {
    		System.out.println(header.getName()+"----"+header.getValue()+"  ");
		}     
    	System.out.println(response.getStatusLine().getStatusCode());
    	String rteurnStr = EntityUtils.toString(response.getEntity());	
    	System.out.println(rteurnStr);
    	
    	InetAddress[] address = InetAddress.getAllByName(strUrl);
		  	for (int i = 0; i < address.length; i++) {
		  		System.out.println(address[i]);
		  	}		  	
		  return rteurnStr;
	}
	
	

}
