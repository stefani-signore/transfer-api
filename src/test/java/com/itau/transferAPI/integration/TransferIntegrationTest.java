package com.itau.transferAPI.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TransferIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldTransferSuccessfully() throws Exception {

        mockMvc.perform(post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "name":"Origem",
                      "accountNumber":"11111",
                      "balance":5000
                    }
                    """))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "name":"Destino",
                      "accountNumber":"22222",
                      "balance":1000
                    }
                    """))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "sourceAccount":"11111",
                      "destinationAccount":"22222",
                      "amount":1000
                    }
                    """))
                .andExpect(status().isCreated());

    }
}
