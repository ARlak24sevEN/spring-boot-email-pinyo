package com.iamnbty.training.backend;

import com.iamnbty.training.backend.business.EmailBusiness;
import com.iamnbty.training.backend.exception.BaseException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestEmailBusiness {

    @Autowired
    private EmailBusiness emailBusiness;

    @Order(1)
    @Test
    void testSendActivateEmail() throws BaseException {
        emailBusiness.sendActivateUserEmail(
                TestData.email,
                TestData.name,
                TestData.token
        );
    }

    interface TestData {

        String email = "smileroom28806@gmail.com";

        String name = "Arlak Abdulloh";

        String token = "/watch?v=MNuVmrqw7Lo";

    }

}