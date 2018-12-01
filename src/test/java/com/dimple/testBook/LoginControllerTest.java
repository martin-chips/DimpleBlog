package com.dimple.testBook;

import com.dimple.controller.LoginController;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @ClassName: LoginControllerTest
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/11/27 22:29
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get("/user/{id}", 1);
        ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
        perform.andExpect(content().string(String.valueOf(1)));
        mockMvc.perform(get("/user.html").content("json"));

        perform.andExpect(status().isOk());//期望成功调用，即HTTP Status 为200
        perform.andExpect(content().contentType(MediaType.APPLICATION_JSON));//期望返回的内容的类型为application/json
        perform.andExpect(jsonPath("$.name").value("jsonabc"));//检查返回内容
        perform.andExpect(view().name("/success.html"));//检验返回的视图
        //比较model
        perform.andExpect(model().size(1));
        perform.andExpect(model().attributeExists("person"));
        perform.andExpect(model().attribute("person", "xiaodafu"));
        //比较forward和redirect
        perform.andExpect(forwardedUrl("index.html"));//或者
        redirectedUrl("index.html");
        //比较返回内容
        perform.andExpect(content().string("hello world"));
        //返回内容是JSON，并且与JsonContent内容一样
        perform.andExpect(content().json(""));
    }
}
