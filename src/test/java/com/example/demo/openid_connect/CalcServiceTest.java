package com.example.demo.openid_connect;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.example.demo.openid_connect.dto.CalcResult;
import com.example.demo.openid_connect.properties.CoogleConnProperties;
import com.example.demo.openid_connect.service.CalcService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, value = { "client_id=id", "client_secret=secret" })
public class CalcServiceTest {

	@Autowired
	private CalcService calcService;

	@Autowired
	MockMvc mvc;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CoogleConnProperties connProps;

	@Test
	public void testCalcService() {
		CalcResult res = calcService.calcExpression("(2+2)*2/4");
		assertEquals(2.0, res.getResult().doubleValue(), 0);
	}

	@Test
	public void testCalcRedirect() throws Exception {
		// Locally it's only possible test redirect from /calc -> /login
		RequestBuilder requestBuilder = get("/calc");
		mockMvc.perform(requestBuilder).andExpect(redirectedUrl("http://localhost/login"))
				.andExpect(status().is(HttpStatus.FOUND.value()));
	}

	@Test
	public void testLoginRedirect() throws Exception {
		// Locally it's only possible test redirect from /login -> google authorization url
		RequestBuilder requestBuilder = get("/login");
		mockMvc.perform(requestBuilder).andExpect(redirectedUrlPattern(connProps.getUserAuthorizationUri() + 
				"?client_id=id&redirect_uri=http://localhost/login&response_type=code&scope=openid&state=*"))
				.andExpect(status().is(HttpStatus.FOUND.value()));
	}
}
