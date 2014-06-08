package com.ds.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.ds.config.ConfigManager;
import com.ds.constant.ConfigConstants;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class ResourceHelper {
	/**
	 * Path to root folder for storing file.
	 */
	public static final String RESOURCE_ROOT_PATH =  ConfigManager.getInstance().getProperty(ConfigConstants.RESOURCE_ROOT_PATH);
	
	public static final String PHOTO_FOLDER = "Photo";
	/**
	 * Build the path for saving new resource. Create new folder for saving file if need.
	 * Structure of path to resource:
	 * + [RESOURCE_ROOT_PATH]/[PHOTO_FOLDER]/[YEAR]/[MONTH][/DAY]/FILE_INDEX.[EXTENTION]
	 * @return
	 */
	public static String preparePathForNewResource(String extention) {
		StringBuffer path = new StringBuffer(RESOURCE_ROOT_PATH + File.separator);
		
		path.append(PHOTO_FOLDER + File.separator);
		
		String yearMonthDayPath = buildYearMonthDayFoldersPath(new Date());
		path.append(yearMonthDayPath + File.separator);
		
		String placeHolderFolder = path.toString();
		
		File folder = new File(placeHolderFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		
		int availabeIndex = FileUtil.retrieveAvailableFileIndex(placeHolderFolder);
		path.append(availabeIndex);
		
		if (!StringUtils.isEmpty(extention)) {
			path.append("." + extention);
		}
		
		return path.toString();
	}
	
	/**
	 * Build [YEAR]/[MONTH][/DAY] string base on inputed Date
	 * 
	 * @param date
	 * @return
	 */
	public static String buildYearMonthDayFoldersPath(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return String.format("%s" + File.separator + "%s" + File.separator + "%s", 
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
	}
}
