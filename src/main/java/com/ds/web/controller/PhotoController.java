package com.ds.web.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.service.IResourceService;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
@Controller
public class PhotoController {
	@Autowired
	IResourceService resourceService;
	

}
