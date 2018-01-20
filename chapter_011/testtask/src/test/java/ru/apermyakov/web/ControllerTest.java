package ru.apermyakov.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.apermyakov.domain.account.Account;
import ru.apermyakov.domain.shortened.Shortened;
import ru.apermyakov.service.AccountsDAOImpl;
import ru.apermyakov.service.ShortenedsDAOImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShortenController.class, secure = false)
public class ControllerTest {

    @Autowired
    private MockMvc mvcMock;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AccountsDAOImpl accountService;

    @MockBean
    private ShortenedsDAOImpl shortenedService;

    @Test
    public void whenGetToAccountThenOkAndAccount() throws Exception {
        this.mvcMock.perform(
                get("/account").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("account")
        );
    }

    @Test
    public void whenCreateAccountThenOnesCreate() throws Exception {
        Account account = new Account();
        account.setAccountId("Ivan");
        this.mvcMock.perform(
                post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account))
        ).andExpect(
                status().isOk()
        );
        verify(this.accountService, times(1)).create(account);
    }

    @Test
    public void whenGetToRegisterThenOkAndRegister() throws Exception {
        this.mvcMock.perform(
                get("/register").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("register")
        );
    }

    @Test
    public void whenCreateRegisterThenOnesCreate() throws Exception {
        Shortened shortened = new Shortened();
        shortened.setLongUrl("LongUrl");
        this.mvcMock.perform(
                post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shortened))
        ).andExpect(
                status().isOk()
        );
        verify(this.shortenedService, times(1)).create(shortened);
    }
}