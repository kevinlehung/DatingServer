package com.ds.web.webservice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ds.persist.domain.UserPhoto;
import com.ds.security.bean.DsUserDetails;
import com.ds.service.IResourceService;
import com.ds.web.util.SecurityUtil;
import com.ds.web.webservice.bean.BaseWsBean;
import com.ds.web.webservice.bean.PhotoWsBean;
import com.ds.web.webservice.util.WsUtil;

/**
 * 
 * @author hunglevn@outlook.com
 * 
 */
@RestController
public class PhotosWs extends BaseWs {
	@Autowired
	@Qualifier("dsAuthenticationManager")
	protected AuthenticationManager authenticationManager;

	@Autowired
	IResourceService resourceService;

	@RequestMapping(value = "/ws/add_photos", method = {RequestMethod.POST}, produces = {
			"application/xml", "application/json" })
	public List<PhotoWsBean> doAddPhotos(@RequestParam("files") List<MultipartFile> files, String fileName) {
		List<PhotoWsBean> photoWsBeans = new ArrayList<PhotoWsBean>();
		DsUserDetails userDetail = SecurityUtil.getUserDetail();
		int userId = userDetail.getDsUser().getUserId();
		for (MultipartFile file : files) {
			UserPhoto userPhoto = resourceService.addPhoto(userId, file, fileName);
			
			PhotoWsBean photoWsBean = new PhotoWsBean();
			int resourceId = userPhoto.getResource().getResourceId();
			photoWsBean.setResourceId(resourceId);
			
			photoWsBeans.add(photoWsBean);
		}
		return photoWsBeans;
	}
	
	@RequestMapping(value = "/ws/retrieve_photo", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public FileSystemResource retrievePhoto(@RequestParam("resourceId") int resourceId) {
		File photoFile = resourceService.getResourceFile(resourceId);
		return new FileSystemResource(photoFile);
	}
	
}