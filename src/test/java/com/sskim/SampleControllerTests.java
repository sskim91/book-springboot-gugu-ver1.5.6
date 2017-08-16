package com.sskim;

import com.sskim.controller.SampleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(SampleController.class)
public class SampleControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testHello() throws Exception {

        //mockMvc.perform(get("/hello")).andExpect(content().string("hello world"));

        MvcResult result = mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World")).andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

}
