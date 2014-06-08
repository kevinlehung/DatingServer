package com.ds.web.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ds.persist.domain.Resource;
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
public class AddPhotosWs extends BaseWs {
	@Autowired
	@Qualifier("dsAuthenticationManager")
	protected AuthenticationManager authenticationManager;

	@Autowired
	IResourceService resourceService;

	@RequestMapping(value = "/ws/add_photo", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public PhotoWsBean doAddPhoto(@RequestParam("file") MultipartFile file, String fileName) {
		PhotoWsBean photoWsBean = new PhotoWsBean();
		String statusMessage;
		String processStatus;
		if (!file.isEmpty()) {
			DsUserDetails userDetail = SecurityUtil.getUserDetail();
			int userId = userDetail.getDsUser().getUserId();
			resourceService.addPhoto(userId, file, fileName);
			processStatus = BaseWsBean.ProcessStatus.SUCCESSED;
			statusMessage = "File is uploaded successfully.";
		} else {
			statusMessage = "Failed to upload. File was empty.";
			log.warn(statusMessage);
			processStatus = BaseWsBean.ProcessStatus.FAILED;
		}
		
		WsUtil.setWsResult(photoWsBean, processStatus, statusMessage);
		return photoWsBean;
	}
}