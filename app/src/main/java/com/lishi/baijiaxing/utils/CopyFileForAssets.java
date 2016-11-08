package com.lishi.baijiaxing.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CopyFileForAssets {
	/**
	 * 复制assets文件到 指定目录
	 * 
	 * @param mContext
	 * @param assetsName
	 * @param savePath
	 * @param saveName
	 */
	public static void copy(Context mContext, String assetsName,
			String savePath, String saveName) {
		String filename = savePath + "/" + saveName;
		File dir = new File(savePath);
		// 如果目录不中存在，创建这个目录
		if (!dir.exists())
			dir.mkdir();
		try {
			if (!(new File(filename)).exists()) {
				InputStream is = mContext.getResources().getAssets()
						.open(assetsName);
				FileOutputStream fos = new FileOutputStream(filename);
				byte[] buffer = new byte[2048];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
