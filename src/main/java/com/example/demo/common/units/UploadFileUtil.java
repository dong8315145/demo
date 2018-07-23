package com.example.demo.common.units;

import com.example.demo.common.config.UploadFileConfig;
import com.example.demo.dao.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.krb5.Config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadFileUtil {

	private static String ROOTDIR = null;
	private static final String ROOTDIR_NAME = "file.rootDir";
	private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");


	/**
	 * 文件类型-受理文件
	 */
	public static final String FILE_TYPE_ACCEPTANCE = "acceptanceFile";

	public static String getRootDir() {
		if (ROOTDIR == null) {
			ROOTDIR =new UploadFileConfig().getUploadfilepath();
		}
		return ROOTDIR;
	}

	/**
	 * 创建根目录
	 * 
	 * @param
	 * @return
	 */
	public static String getFileDir(Object o) {
		String dirName = "";
		if (o == null) {
			dirName = "public";
		}
		Date date = new Date();
		StringBuilder dir = new StringBuilder();
		dir.append("/" + YEAR_FORMAT.format(date));
		dir.append("/" + dirName);
		mkdirs(getRootDir() + dir.toString());
		return dir.toString();
	}

	/**
	 * 用于模板管理 上传到指定目录
	 * 
	 * @param o
	 * @param type
	 * @return
	 */
	public static String getTemplateDir(Object o, String type) {
		String dirName = "";
		if (o == null) {
			dirName = "public";
		} else {
			dirName = o.toString();
		}
		StringBuilder dir = new StringBuilder();
		String typeStr = "template." + type;
		dir.append(dirName +"/");
		mkdirs(dir.toString());
		return dir.toString();
	}


	/**
	 * 创建路径下所有文件夹
	 * 
	 * @param dir
	 */
	public static void mkdirs(String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * Java文件操作 获取文件扩展名
	 * 
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * Java文件操作 获取不带扩展名的文件名
	 * 
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
}
