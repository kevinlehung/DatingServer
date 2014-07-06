package com.ds.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ds.persist.domain.Resource;
import com.ds.persist.domain.User;
import com.ds.persist.domain.UserPhoto;
import com.ds.persist.repositories.ResourceRepo;
import com.ds.persist.repositories.UserPhotoRepo;
import com.ds.util.ResourceHelper;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
@Service
public class ResourceService extends BaseService implements IResourceService {
	@Autowired@Qualifier("resourceRepo")
	private ResourceRepo resourceRepo;
	
	@Autowired@Qualifier("userPhotoRepo")
	private UserPhotoRepo userPhotoRepo;
	
	public UserPhoto addPhoto(int userId, MultipartFile file, String fileName) {
		Resource resource = new Resource();
		String photoPath = addPhotoToFileSystem(file, fileName);
		
		resource.setPath(photoPath);
		if (fileName != null) {
			resource.setResourceName(fileName);
		} else {
			resource.setResourceName(file.getOriginalFilename());
		}
		resource.setCreatedDate(new Date());
		
		resourceRepo.save(resource);
		
		User user = new User();
		user.setUserId(userId);
		
		UserPhoto userPhoto = userPhotoRepo.addUserPhoto(user, resource);
		return userPhoto;
		
	}

	private String addPhotoToFileSystem(MultipartFile file, String fileName) {
		OutputStream output = null;
		try {
			byte[] data = file.getBytes();
			String extention = FilenameUtils.getExtension(fileName);
			String outputFile = ResourceHelper.preparePathForNewResource(extention);
			output = new FileOutputStream(new File(outputFile));
			IOUtils.write(data, output);
			return outputFile;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to add photo [name = %s]", fileName), e);
		} finally {
			IOUtils.closeQuietly(output);
		}
	}

	public File getResourceFile(int resourceId) {
		Resource resource = resourceRepo.findOne(resourceId);
		if (resource != null) {
			File resourceFile = new File(resource.getPath());
			return resourceFile.exists() ? resourceFile : null;
		}
		return null;
	}
}
