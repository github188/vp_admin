/**
 * <p>Title: ComplexButton.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: Consmation</p>
 * @author ������
 * @date 2015��12��7��
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.menu;

/**
 * <p>Title: ComplexButton</p>
 * <p>Description: </p>
 * @author ������
 * @date 2015��12��7��
 */
public class ComplexButton extends Button{
	private Button[] sub_button;

	/**
	 * @return sub_button
	 */
	public Button[] getSub_button() {
		return sub_button;
	}

	/**
	 * @param sub_button the sub_button to set
	 */
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
	

}
