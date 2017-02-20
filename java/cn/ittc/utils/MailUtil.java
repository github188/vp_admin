/**
 * <p>Title: MailUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬�?
 * @date 2016�?�?�?
 * @version 1.0.0
 */
package cn.ittc.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * <p>Title: MailUtil</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��6��7��
 */
public class MailUtil {
	static int port = 25;
	 
    static String server = "smtp.sina.com";//�ʼ�������mail.cpip.net.cn
 
    static String from = "ƽ̨����";//������,��ʾ�ķ���������
 
    static String user = "jellyjiao5210@sina.com";//�����������ַ
 
    static String password = "wb_jdd5210";//����
    
    public static void sendEmail(String email, String subject, String body) throws UnsupportedEncodingException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", server);
            props.put("mail.smtp.port", String.valueOf(port));
            props.put("mail.smtp.auth", "true");
            Transport transport = null;
            Session session = Session.getDefaultInstance(props, null);
            transport = session.getTransport("smtp");
            transport.connect(server, user, password);
            MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            InternetAddress fromAddress = new InternetAddress(user,from,"UTF-8");
            msg.setFrom(fromAddress);
            InternetAddress[] toAddress = new InternetAddress[1];
            toAddress[0] = new InternetAddress(email);
            msg.setRecipients(Message.RecipientType.TO, toAddress);
            msg.setSubject(subject, "UTF-8");   
            msg.setText(body, "UTF-8");
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) throws UnsupportedEncodingException, InterruptedException
    {
    	for(int i=0;i<100;i++){
    		Thread.sleep(1000*10);
    		 sendEmail("2734698945@qq.com","�ʼ�����","���㲻��Ϊ���绰Ҳ���ӣ�");//�ռ���
    	        System.out.println("ok"+i);
    	}
       
    }
}
