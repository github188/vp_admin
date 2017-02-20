/**
 * <p>Title: CreateMenu.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��30��
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.menu;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.ittc.weixin.utils.Constants;
import cn.ittc.weixin.utils.TokenListener;
import cn.ittc.weixin.utils.WechatMenu;
import net.sf.json.JSONObject;

/**
 * <p>Title: TestMenu</p>
 * <p>Description: </p>
 * @author ������
 * @date 2015��12��7��
 */
public class CreateMenu {
	
	private static Menu getMenu(){
		Menu menu=new Menu();
		CommonButton btn11=new CommonButton();
		btn11.setName("��˾����");
		btn11.setUrl("http://www.ittc.sh.cn/shjm/index.asp");
		btn11.setType("view");
		CommonButton btn12=new CommonButton();
		btn12.setName("���ڼ�ó");
		btn12.setUrl("http://www.ittc.sh.cn/shjm/about.asp");
		btn12.setType("view");
		CommonButton btn13=new CommonButton();
		btn13.setName("�������");
		btn13.setUrl("http://www.ittc.sh.cn/shjm/solution.asp");
		btn13.setType("view");
		CommonButton btn14=new CommonButton();
		btn14.setName("�ɹ�����");
		btn14.setUrl("http://www.ittc.sh.cn/shjm/success.asp");
		btn14.setType("view");
		CommonButton btn15=new CommonButton();
		btn15.setName("��������");
		btn15.setUrl("http://www.ittc.sh.cn/shjm/about1.asp");
		btn15.setType("view");
		ComplexButton mainBtn1=new ComplexButton();
		mainBtn1.setName("���ʼ�ó");
		
		mainBtn1.setSub_button(new CommonButton[]{btn11,btn12,btn13,btn14,btn15});
		
		CommonButton btn21=new CommonButton();
		btn21.setName("ʵʱ�켣");
//		btn21.setUrl(Constants.serviceUrl+"trace.jsp");
		btn21.setUrl(Constants.OAUTH2_URL.replace("TAG", WechatMenu.TRACE.getName()));
		btn21.setType("view");
		CommonButton btn22=new CommonButton();
		btn22.setName("��ʷ�켣");
//		btn22.setUrl(Constants.serviceUrl+"blm.jsp");
		btn22.setUrl(Constants.OAUTH2_URL.replace("TAG", WechatMenu.BLM.getName()));
		btn22.setType("view");
		
		CommonButton btn23=new CommonButton();
		btn23.setName("�豸��λ");
//		btn23.setUrl(Constants.serviceUrl+"gps.jsp");
		btn23.setUrl(Constants.OAUTH2_URL.replace("TAG", WechatMenu.GPS.getName()));
		btn23.setType("view");
		
		CommonButton btn24=new CommonButton();
		btn24.setName("Ȩ�޹���");
		btn24.setUrl(Constants.OAUTH2_URL.replace("TAG", WechatMenu.RBAC.getName()));
		btn24.setType("view");
//		CommonButton btn25=new CommonButton();
//		btn25.setName("��־����");
//		btn25.setUrl(Constants.OAUTH2_URL.replace("TAG", WechatMenu.LOG.getName()));
//		btn25.setType("view");
	
		ComplexButton mainBtn2=new ComplexButton();
		mainBtn2.setName("����װ��");
//		mainBtn2.setSub_button(new CommonButton[]{btn21,btn22,btn23,btn24,btn25});
		mainBtn2.setSub_button(new CommonButton[]{btn21,btn22,btn23,btn24});
		
		
//		CommonButton btn31=new CommonButton();
//		btn31.setName("Ȩ�޹���");
////		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri="
////				+ Constants.serviceUrl
////				+ "go_rbac.action&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
//		btn31.setUrl(Constants.OAUTH2_URL.replace("TAG", WechatMenu.RBAC.getName()));
////			btn31.setUrl(url.replace("APPID", Constants.APPID));
//		btn31.setType("view");
		CommonButton btn32=new CommonButton();
		btn32.setName("�����ѯ");
		btn32.setUrl(Constants.serviceUrl+"express.jsp");
		btn32.setType("view");
		CommonButton btn33=new CommonButton();
		btn33.setName("��ϵ����");
		btn33.setUrl("http://www.ittc.sh.cn/shjm/contact.asp");
		btn33.setType("view");
		ComplexButton mainBtn3=new ComplexButton();
		
		mainBtn3.setSub_button(new CommonButton[]{btn33,btn32});
//		mainBtn3.setSub_button(new CommonButton[]{btn31,btn32,btn33});
		mainBtn3.setName("��ϵ����");
		
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		return menu;
	}
	
	public static void createMenu() throws ClientProtocolException, IOException {

		String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ TokenListener.accessToken.getAccessToken();

		// JsonParser jsonparer = new JsonParser();// ��ʼ������json��ʽ�Ķ���
		JSONObject jsonObject = JSONObject.fromObject(getMenu());
		System.out.println(jsonObject);

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(requestUrl);
		String responseContent = null; // ��Ӧ����
		StringEntity s = new StringEntity(jsonObject.toString(), "UTF-8");
		s.setContentEncoding("UTF-8");

		s.setContentType("application/json");
		post.setEntity(s);
		HttpResponse res = client.execute(post);
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// HttpEntity entity=res.getEntity();
			String result = EntityUtils.toString(res.getEntity());
			responseContent = JSONObject.fromObject(result).toString();
			System.err.println(responseContent);
		}
	}
	
	public static void delete() throws ClientProtocolException, IOException{
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN",
						TokenListener.accessToken.getAccessToken());
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		String responseContent = null; // ��Ӧ����
		//StringEntity s = new StringEntity(jsonObject.toString(), "UTF-8");
		//s.setContentEncoding("UTF-8");

		//s.setContentType("application/json");
		//post.setEntity(s);
		HttpResponse res = client.execute(get);
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// HttpEntity entity=res.getEntity();
			String result = EntityUtils.toString(res.getEntity());
			responseContent = JSONObject.fromObject(result).toString();
			System.err.println(responseContent);
		}
	}
	
	//
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		createMenu();
		
//		HttpClient client = new DefaultHttpClient();
//		HttpPost post = new HttpPost(requestUrl);
//		
//		
//		HttpResponse res = client.execute(post);
//		String responseContent = null; // ��Ӧ����
//		HttpEntity entity = res.getEntity();
//		responseContent = EntityUtils.toString(entity, "UTF-8");
//		JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();
//		System.out.println(json);
	}

}
