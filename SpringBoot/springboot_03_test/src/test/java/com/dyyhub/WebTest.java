package com.dyyhub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

//开启web环境下的随机端口
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WebTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    //注入虚拟mvc对象
    public void testWeb() throws Exception {
        //创建虚拟请求，当前访问/users
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users");
        //执行请求
        ResultActions actions = mockMvc.perform(builder);
    }

    @Test
    public void testStatus() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users1");
        ResultActions actions = mockMvc.perform(builder);
        //匹配执行状态
        //定义执行状态匹配器
        StatusResultMatchers resultMatchers = MockMvcResultMatchers.status();
        //定义预期执行状态
        ResultMatcher ok = resultMatchers.isOk();
        //使用本次真实执行结果与预期结果进行对比
        actions.andExpect(ok);
    }

    @Test
    public void testContent() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users");
        ResultActions actions = mockMvc.perform(builder);
        //匹配执行状态
        //定义执行状态匹配器
        ContentResultMatchers resultMatchers = MockMvcResultMatchers.content();
        //定义预期执行状态
            ResultMatcher ok = resultMatchers.string("SpringBoot");
        //使用本次真实执行结果与预期结果进行对比
        actions.andExpect(ok);
    }

    @Test
    public void testJson() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/users/1");
        ResultActions actions = mockMvc.perform(builder);
        //匹配执行状态
        //定义执行状态匹配器
        ContentResultMatchers resultMatchers = MockMvcResultMatchers.content();
        //定义预期执行状态
        ResultMatcher ok = resultMatchers.json("{\"id\":1,\"name\":\"Tom2\",\"age\":18}");
        //使用本次真实执行结果与预期结果进行对比
        actions.andExpect(ok);
    }


}
