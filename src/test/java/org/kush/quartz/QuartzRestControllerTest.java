package org.kush.quartz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kush.quartz.controller.PriceChangeController;
import org.kush.quartz.service.PriceChangeService;
import org.kush.quartz.vo.PriceChange;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(PriceChangeController.class)
public class QuartzRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceChangeService mockService;

    @BeforeAll
    static public void setup(){

    }
    @Test
    public void testGetUsers() throws Exception {
        // Configure mock service behavior (if needed)
        List<PriceChange> mockPriceList = Arrays.asList(new PriceChange(20.0), new PriceChange(10.0));
        ObjectMapper mapper = new ObjectMapper();
       String mockpriceString= mapper.writeValueAsString(mockPriceList);
        String jobName="testName";
        String jobGroup="testJobGroup";
        Mockito.when(mockService.triggerJob(jobName,jobGroup,mockPriceList)).thenReturn("success");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/priceChange").content(mockpriceString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        // Verify response body
        String response = result.getResponse().getContentAsString();
        assertThat(response).isEqualTo("Job triggered successfully");
    }
}
