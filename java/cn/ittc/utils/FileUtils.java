/**
 * <p>Title: FileUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬�?
 * @date 2015�?1�?7�?
 * @version 1.0.0
 */
package cn.ittc.utils;

import java.io.File;
/**
 * <p>Title: FileUtils</p>
 * <p>Description: </p>
 * @author ������
 * @date 2015��11��27��
 */
public class FileUtils {

	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// �ݹ�ɾ��Ŀ¼�е���Ŀ¼��
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// Ŀ¼��ʱΪ�գ�����ɾ��
		return dir.delete();
	}
	public static void main(String[] args) {
		File file=new File("");
		FileUtils.deleteDir(file);
	}
}
