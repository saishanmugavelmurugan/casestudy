package com.rbs.api;


import com.rbs.entity.TransactionRepository;
import com.rbs.model.RestApiStatus;
import com.rbs.model.Transaction;
import com.rbs.service.TransactionService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private TransactionService ts;
    @Autowired
    TransactionRepository transactionRepository;
    @BeforeTestMethod
    private void setup(){
       // transactionRepository.deleteAll();
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();

    }
    @Test
    public void transferAmount_ok() throws Exception {
        //Transaction mockTransaction = new Transaction("testrbs12345", "rbs123456",Double.valueOf(20));
        RestApiStatus rs=new RestApiStatus(HttpStatus.OK,"200","success","Transaction successfull.");
        ResponseEntity re=new ResponseEntity(rs,HttpStatus.OK);
        String transactionJson = "{\"sourceAccountNumber\":\"testrbs12345\",\"destinationAccountNumber\":\"testrbs123456\",\"amount\":10}";
        when(ts.transferAmount(any(Transaction.class))).thenReturn(rs);
        MvcResult mr=mockMvc.perform(post("/sendAmount").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        System.out.println(mr.getResponse().getContentAsString());
        Assert.assertEquals(true, mr.getResponse().getContentAsString().contains("success"));
    }
    @Test
    public void transferAmount_account_notavailable() throws Exception {
        Transaction mockTransaction = new Transaction("rbs12345", "rbs123456",Double.valueOf(20));
        RestApiStatus rs=new RestApiStatus(HttpStatus.FORBIDDEN,"300","Failed","Source or destination account not found.");
        ResponseEntity re=new ResponseEntity(rs,HttpStatus.OK);
        String transactionJson = "{\"sourceAccountNumber\":\"rbs12345\",\"destinationAccountNumber\":\"rbs123456\",\"amount\":10}";
        when(ts.transferAmount(any(Transaction.class))).thenReturn(rs);
        MvcResult mr=mockMvc.perform(post("/sendAmount").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        System.out.println(mr.getResponse().getContentAsString());
        Assert.assertEquals(true, mr.getResponse().getContentAsString().contains("Failed"));
    }
    @Test
    public void transferAmount_fund_notavailable() throws Exception {
        Transaction mockTransaction = new Transaction("rbs12345", "rbs123456",Double.valueOf(20));
        RestApiStatus rs=new RestApiStatus(HttpStatus.FORBIDDEN,"300","Failed","Source account has insufficient fund");
        ResponseEntity re=new ResponseEntity(rs,HttpStatus.OK);
        String transactionJson = "{\"sourceAccountNumber\":\"rbs12345\",\"destinationAccountNumber\":\"rbs123456\",\"amount\":10}";
        when(ts.transferAmount(any(Transaction.class))).thenReturn(rs);
        MvcResult mr=mockMvc.perform(post("/sendAmount").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        System.out.println(mr.getResponse().getContentAsString());
        Assert.assertEquals(true, mr.getResponse().getContentAsString().contains("Failed"));
    }


}
