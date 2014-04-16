package com.ds;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ds.persist.repositories.RepoConfig;
import com.ds.spring.context.CacheConfig;
import com.ds.spring.context.ServiceConfig;
import com.ds.spring.context.SpringConfig;
import com.ds.web.config.WebConfig;

/**
 *
 * @author hunglevn@outlook.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class, CacheConfig.class, RepoConfig.class, ServiceConfig.class})
public class BaseTester {
	@Resource
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMVC;

    @Before
    public void setUp() {
        mockMVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
