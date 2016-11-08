package com.lishi.baijiaxing.utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtil {

	private static String SDPATH = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/";

	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public static File creatSDFile(String saveFileName) throws IOException {
		File file = new File(SDPATH + saveFileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 删除文件
	 * 
	 * @param path2
	 */
	public static void DeleteFileName(String path2) {
		String path = SDPATH + path2 + "/";
		File file = new File(path);
		if (file != null)
			deleteFile(file);
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	private static void deleteFile(File file) {
		if (file.exists() == false) {
			return;
		} else {
			if (file.isFile()) { // 是文件
				file.delete();// 删除文件
				return;
			} else if (file.isDirectory()) { // 是目录
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					file.delete();// 删除本身
					return;
				}
				for (File f : childFile) {
					deleteFile(f);
				}
				file.delete();// 删除本身
			}
		}
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 */
	public static File creatSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		dir.mkdir();
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public static Uri getUri(String fileName) {
		File file = new File(SDPATH + fileName);
		return Uri.fromFile(file);
	}

	/**
	 * 由于得到一个InputStream对象是所有文件处理前必须的操作，所以将这个操作封装成了一个方法
	 * 
	 * @param urlStr
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStream(String urlStr) throws IOException {
		InputStream is = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			is = urlConn.getInputStream();
			int fileSize = urlConn.getContentLength();// 根据响应获取文件大小
			if (fileSize <= 0)
				throw new RuntimeException("无法获知文件大小 ");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return is;
	}
}
