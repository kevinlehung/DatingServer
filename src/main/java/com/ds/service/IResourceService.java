package com.ds.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.ds.persist.domain.UserPhoto;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public interface IResourceService extends IBaseService {

	UserPhoto addPhoto(int userId, MultipartFile file, String filename);

	File getResourceFile(int resourceId);
	
}
