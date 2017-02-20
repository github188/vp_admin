/**
 * <p>Title: FileUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 鐒﹀啲鍐?
 * @date 2015骞?1鏈?7鏃?
 * @version 1.0.0
 */
package cn.ittc.utils;

import java.io.File;
/**
 * <p>Title: FileUtils</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2015年11月27日
 */
public class FileUtils {

	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
	public static void main(String[] args) {
		File file=new File("");
		FileUtils.deleteDir(file);
	}
}
