package com.ds.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ds.constant.WebConstants;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
@Controller
public class HTTPErrorController {

    @RequestMapping(value="/errors")
    public String handle404() {
    	return WebConstants.Views.ERROR;
    }

}