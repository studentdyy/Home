package com.hmdp.script;


import cn.hutool.json.JSONUtil;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.entity.User;
import com.hmdp.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.io.*;

/**
 * @author dyyhub
 * @date 2022年05月15日 8:48
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserToken {
    @Autowired
    MockMvc mockMvc;
    @Resource
    IUserService userService;


    @Test
    public void getToken() throws Exception {
        String phone = "";
        String code = "";

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("e:\\dyyhub\\token.txt"));

        for (int i = 10; i < 1010; i++) {
            //通过id从数据库中获得user对象
            User user = userService.getById(i);
            phone = user.getPhone();
            //创建虚拟请求，模拟通过手机号，发送验证码
            ResultActions perform1 = mockMvc.perform(MockMvcRequestBuilders
                    .post("/user/code?phone=" + phone));
            //获得Response的body信息
            String resultJson1 = perform1.andReturn().getResponse().getContentAsString();
            //将结果转换为result对象
            Result result = JSONUtil.toBean(resultJson1, Result.class);
            //获得验证码
            code = result.getData().toString();
            //创建登录表单
            LoginFormDTO loginFormDTO = new LoginFormDTO();
            loginFormDTO.setCode(code);
            loginFormDTO.setPhone(phone);
            //将表单转换为json格式的字符串
            String loginFormDtoJson = JSONUtil.toJsonStr(loginFormDTO);
            //创建虚拟请求，模拟登录
            ResultActions perform2 = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                    //设置contentType表示为json信息
                    .contentType(MediaType.APPLICATION_JSON)
                    //放入json对象
                    .content(loginFormDtoJson));

            String resultJson2 = perform2.andReturn().getResponse().getContentAsString();
            Result result2 = JSONUtil.toBean(resultJson2, Result.class);
            //获得token
            String token = result2.getData().toString();
            //写入
            osw.write(token+"\n");
        }
        //关闭输出流
        osw.close();
    }




    @Test
    public void testWeb3() throws IOException {
        try{
        File f = new File("e:/dyyhub/token.txt");
        FileWriter fr = new FileWriter(f);

        String data="abcdefg1234567890";
        char[] cs = data.toCharArray();
        fr.write(cs);
        fr.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testWeb4() throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("e:\\dyyhub\\token.txt"));
        for (int i = 0; i < 10; i++) {
            osw.write("abcde+" + i +"\n");
            osw.write("fdjasknmb\n");
            osw.write("哈哈哈我是卖报的小行家！\n");
        }

        osw.close();
    }


    @Test
    //注入虚拟mvc对象
    public void testWeb() throws Exception {
        //创建虚拟请求，当前访问/users
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/voucher-order/seckill/15");

        builder.header("authorization","7a75a71346c6481b8053c97445a62fc9");
        builder.characterEncoding("utf-8");
        //执行请求
        ResultActions actions = mockMvc.perform(builder);
        ContentResultMatchers resultMatchers = MockMvcResultMatchers.content();
        ResultMatcher ok = resultMatchers.json("{\"id\":1,\"name\":\"Tom2\",\"age\":18}");
        //使用本次真实执行结果与预期结果进行对比
        actions.andExpect(ok);
    }


    @Test
    //注入虚拟mvc对象
    public void testWeb2() throws Exception {
        String phone = "13456789001";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/code?phone="+phone);
        builder.characterEncoding("utf-8");
        //执行请求
        ResultActions actions = mockMvc.perform(builder);
        ContentResultMatchers content = MockMvcResultMatchers.content();
        content.encoding("utf-8");
        MvcResult mvcResult = actions.andReturn();
        Result result1 = JSONUtil.toBean(mvcResult.getResponse().getContentAsString(), Result.class);
        System.out.println(result1.getData().toString());


        LoginFormDTO loginFormDTO = new LoginFormDTO();
        loginFormDTO.setCode(result1.getData().toString());
        loginFormDTO.setPhone(phone);
        String object = JSONUtil.toJsonStr(loginFormDTO);
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/user/login");
        post.contentType(MediaType.APPLICATION_JSON).content(object);
        ResultActions action = mockMvc.perform(post);
        String contentAsString1 = action.andReturn().getResponse().getContentAsString();
        Result result = JSONUtil.toBean(contentAsString1, Result.class);
        System.out.println(result.getData().toString());
    }
}
