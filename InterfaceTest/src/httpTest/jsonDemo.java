package httpTest;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class jsonDemo {
	
	public  static void main(String[] args) {		
		
		   System.out.println("xml�ַ���תjson�ַ���");
		   String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><users><password>123456</password><username>����</username></users>";
		   XMLSerializer xmlSerializer = new XMLSerializer();
		   JSON  json = xmlSerializer.read(xml);
		   System.out.println("ͨ��XMLת��ΪJSON����"+json.toString());		
		   //����һ��Դ�ӿڵ�JSON��
		   String jsonStr = json.toString();
		   //Դ�ӿ�ת����jsonboject����
		   JSONObject jsonObj = JSONObject.fromObject(jsonStr);		   
		   System.out.println(jsonObj.getString("username"));
		   System.out.println(jsonObj.getString("password"));

	}

}
