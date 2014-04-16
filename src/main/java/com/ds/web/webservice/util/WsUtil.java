package com.ds.web.webservice.util;

import org.springframework.beans.BeanUtils;

import com.ds.security.bean.DsUserDetails;
import com.ds.web.util.SecurityUtil;
import com.ds.web.webservice.bean.BaseWsBean;
import com.ds.web.webservice.bean.UserDetailWsBean;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
public class WsUtil {
	public static UserDetailWsBean buildFailedUserDetailWsBean() {
		UserDetailWsBean userDetailWsBean = new UserDetailWsBean();
		userDetailWsBean.setStatusMessage(BaseWsBean.StatusMessage.FAILED);
		userDetailWsBean.setProcessStatus(BaseWsBean.ProcessageStatus.SUCCESSED);
		return userDetailWsBean;
	}

	public static UserDetailWsBean buildSuccessUserDetailWsBean() {
		UserDetailWsBean userDetailWsBean = new UserDetailWsBean();
		DsUserDetails dsUserDetails = SecurityUtil.getUserDetail();
		
		userDetailWsBean.setUserEmail(dsUserDetails.getUsername());
		return userDetailWsBean;
	}
}
