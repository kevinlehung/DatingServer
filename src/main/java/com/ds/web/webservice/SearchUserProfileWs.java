package com.ds.web.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ds.persist.domain.Profile;
import com.ds.persist.domain.Resource;
import com.ds.persist.domain.User;
import com.ds.service.IUserService;
import com.ds.web.util.RequestUtil;
import com.ds.web.webservice.bean.UserProfileWsBean;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
@RestController
public class SearchUserProfileWs {
	@Autowired@Qualifier("dsAuthenticationManager")
    protected AuthenticationManager authenticationManager;
    
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/ws/search_user_profile", method=RequestMethod.POST, 
			produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
	public List<UserProfileWsBean> doSearchUserProfile(HttpServletRequest request, HttpServletResponse response,
			@QueryParam("gender") String gender, @QueryParam("purpose") String purpose, @QueryParam("from_item") String fromItem, @QueryParam("item_count") String itemCount) {
		Map<String, String> searchCriteria = new HashMap<String, String>();
		searchCriteria.put(IUserService.SEARCH_CRITERIA.GENDER, gender);
		searchCriteria.put(IUserService.SEARCH_CRITERIA.PURPOSE, purpose);
		searchCriteria.put(IUserService.SEARCH_CRITERIA.FROM_ITEM, fromItem);
		searchCriteria.put(IUserService.SEARCH_CRITERIA.ITEM_COUNT, itemCount);
		
		List<Object[]> users = userService.searchUser(searchCriteria);
		
		List<UserProfileWsBean> userProfileWsBeans = buildUserProfileWsBeans(request, users);
		return userProfileWsBeans;
	}

	private List<UserProfileWsBean> buildUserProfileWsBeans(HttpServletRequest request, List<Object[]> usersProperties) {
		Map<Integer, UserProfileWsBean> userProfileWsBeans = new HashMap<Integer, UserProfileWsBean>();
		String applicationUrl = RequestUtil.getApplicationUrl(request);
		for (Object[] userProperties : usersProperties) {
			User user = (User)userProperties[0];
			Resource resource = (Resource)userProperties[1];
			Profile profile = (Profile)userProperties[2];
			
			UserProfileWsBean userProfileWsBean = userProfileWsBeans.get(user.getUserId());
			if (userProfileWsBean == null) {
				userProfileWsBean = new UserProfileWsBean();
				userProfileWsBean.setAboutMe(profile.getAboutMe());
				userProfileWsBean.setFullName(profile.getFullName());
				
				userProfileWsBeans.put(user.getUserId(), userProfileWsBean);
			}
			/**
			 * FIXME: Build photo url base on resourceId
			 */
			//userProfileWsBean.getPhotoUrls().add(resource.getPath());
			if (resource != null) {
				
				String photoUrl = String.format("%s/ws/retrieve_photo.ds?resourceId=%d", 
						applicationUrl, 
						resource.getResourceId());
				userProfileWsBean.getPhotoUrls().add(photoUrl);
			}
		}
		return new ArrayList<UserProfileWsBean>(userProfileWsBeans.values());
	}
}
