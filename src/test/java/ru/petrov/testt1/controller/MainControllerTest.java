package ru.petrov.testt1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    MainController mainController;
    @Autowired
    MockMvc mockMvc;

    @Test
    void countChars_correctRequest_shouldReturnAnswer() throws Exception {
        var requestBuilder = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                            "str": "cbbaaa"
                        }
"""
                );

        mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(
                                """
                                {
                                    "answer": {"a": 3, "b": 2, "c": 1}
                                }
"""
                        )
                );
    }

    @Test
    void countChars_incorrectRequest_shouldReturnBadRequest() throws Exception {
        var requestBuilder = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                            "stra": "cbbaaa"
                        }
"""
                );

        mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(
                                """
                                 {
                                    "error": "Str parameter not found"
                                 }
"""
                        )
                );
    }
}