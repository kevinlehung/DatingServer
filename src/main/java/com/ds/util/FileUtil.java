package com.ds.util;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class FileUtil {
	
	/**
	 * Retrieve next index of files in place holder folder.
	 * If place holder folder does not exists, this method create new one and return 1.
	 * 
	 * @param placeHolderFolder
	 */
	public static int retrieveAvailableFileIndex(String placeHolderFolder) {
		File parentFolder = new File(placeHolderFolder);
		if (!parentFolder.exists()) {
			parentFolder.mkdirs();
			return 1;
		}
		File[] indexedFiles = parentFolder.listFiles(new FileFilter() {

			public boolean accept(File subFile) {
				String baseName = FilenameUtils.getBaseName(subFile.getName());
				try {
					Integer.parseInt(baseName);
				} catch (Exception e) {
					return false;
				}
				return true;
			}
		});
		int retrievedIndex = 0;
		for (File file : indexedFiles) {
			String baseName = FilenameUtils.getBaseName(file.getName());
			int currentIndex = Integer.parseInt(baseName);
			if (retrievedIndex < currentIndex) {
				retrievedIndex = currentIndex;
			}
		}
		retrievedIndex = retrievedIndex + 1;
		return retrievedIndex;
	}

}
