/**
 * <p>Title: PropertiesUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: Consmation</p>
 * @author 焦冬�?
 * @date 2015�?2�?�?
 * @version 1.0.0
 */
package cn.ittc.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



/**
 * <p>Title: PropertiesUtil</p>
 * <p>Description: </p>
 * @author ������
 * @date 2015��12��7��
 */
public  class PropertiesUtil {
	private Properties props;
	private String fileName;
	
	
	public PropertiesUtil(){}
	public PropertiesUtil(String fileName){
		this.fileName=fileName;
		readProperties(fileName);
	}
	
	
	public void readProperties(String fileName){
		try {
			props=new Properties();
			//FileInputStream fis=new FileInputStream(fileName);
//			props.load(fis);
			props.load(getClass().getResourceAsStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key){
		return props.getProperty(key);
	}
	
	public Map<String, String> getAllProperty(){
		Map<String, String> map=new HashMap<String, String>();
		Enumeration<?> enu=props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value=props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}
		
	public void writeProperties(String key,String value){
		try {
			OutputStream fos=new FileOutputStream(fileName);
			props.setProperty(key, value);
			props.store(fos, key);
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * <p>Title: getdefualtPwd</p>
	 * <p>Description:�������ļ�sql_conn_jdbc.properties�л�ȡָ����ָ </p>
	 * @author ��֦��
	 * @date 2016��8��9��
	 */
	
	public static String getDefualt(String key){
		Properties prop = new Properties();
		InputStream fis = PropertiesUtil.class.getClassLoader()
				.getResourceAsStream("datasource/sql_conn_jdbc.properties");//����������ļ�
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	
	/**
	 * @return props
	 */
	public Properties getProps() {
		return props;
	}
	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}
	/**
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public static void main(String[] args) {
		
	}
	

}