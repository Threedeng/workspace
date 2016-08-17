package httpTest;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class jsonDemo {
	
	public  static void main(String[] args) {		
		
		   System.out.println("xml字符串转json字符串");
		   String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><users><password>123456</password><username>张三</username></users>";
		   XMLSerializer xmlSerializer = new XMLSerializer();
		   JSON  json = xmlSerializer.read(xml);
		   System.out.println("通过XML转换为JSON串："+json.toString());		
		   //这是一个源接口的JSON串
		   String jsonStr = json.toString();
		   //源接口转换成jsonboject对象
		   JSONObject jsonObj = JSONObject.fromObject(jsonStr);		   
		   System.out.println(jsonObj.getString("username"));
		   System.out.println(jsonObj.getString("password"));
		  
	}

}
