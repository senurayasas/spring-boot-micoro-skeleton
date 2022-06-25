package com.gifted.codes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gifted.codes.repository.CodeRepository;
import com.gifted.test.db.AbstractDatabaseTest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser("user")
@AutoConfigureMockMvc
class TestApplicationTests extends AbstractDatabaseTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CodeRepository codeRepository;

    private static final String BASE_PATH = "/example";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    void afterEach() {
        codeRepository.deleteAll();
    }

}
