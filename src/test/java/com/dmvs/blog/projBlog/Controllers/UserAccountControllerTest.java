package com.dmvs.blog.projBlog.Controllers;

import com.dmvs.blog.projBlog.Models.UserAccount;
import com.dmvs.blog.projBlog.Services.UserAccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("h2")
public class UserAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAccountService userAccountService;

    @Test
    @DisplayName("GET /userAccount/1 - Found")
    public void testFindByIdFound() throws Exception {
        Long givenUserId = 1L;
        UserAccount mockUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        given(userAccountService.findById(givenUserId)).willReturn(Optional.of(mockUser));

        mockMvc.perform(get("/zcwApp/userAccount/{userId}", givenUserId))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$.username", is("Rosalind")))
                .andExpect(jsonPath("$.password", is("rosaPass")))
                .andExpect(jsonPath("$.email", is("rosa@gmail.com")));
    }

    @Test
    @DisplayName("GET /userAccount/1 - Not Found")
    public void testFindByIdNotFound() throws Exception {
        given(userAccountService.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/zcwApp/userAccount/{userId}", 1L))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /userAccount/all - Found")
    public void testFindAllUsers() throws Exception {
        UserAccount user1 = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        UserAccount user2 = new UserAccount(2L, LocalDate.of(2015, 4, 9),
                "Horatio", "horaPass", "hora@gmail.com");
        UserAccount user3 = new UserAccount(3L, LocalDate.of(2015, 4, 9),
                "Hamlet", "hamPass", "hama@gmail.com");
        List<UserAccount> mockUserList = new ArrayList<>(Arrays.asList(user1, user2, user3));
        given(userAccountService.findAllUsers()).willReturn(mockUserList);

        mockMvc.perform(get("/zcwApp/userAccount/all"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$[0].username", is("Rosalind")))
                .andExpect(jsonPath("$[0].password", is("rosaPass")))
                .andExpect(jsonPath("$[0].email", is("rosa@gmail.com")))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$[1].username", is("Horatio")))
                .andExpect(jsonPath("$[1].password", is("horaPass")))
                .andExpect(jsonPath("$[1].email", is("hora@gmail.com")))
                .andExpect(jsonPath("$[2].userId", is(3)))
                .andExpect(jsonPath("$[2].dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$[2].username", is("Hamlet")))
                .andExpect(jsonPath("$[2].password", is("hamPass")))
                .andExpect(jsonPath("$[2].email", is("hama@gmail.com")));

    }

    @Test
    @DisplayName("POST /userAccount/save - Success")
    public void testSaveUserSuccess() throws Exception {
        UserAccount postUser = new UserAccount(LocalDate.of(2015, 4, 9),
                "bento", "bentoPass", "bento@gmail.com");
        UserAccount mockUser = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                "bento", "bentoPass", "bento@gmail.com");
        given(userAccountService.saveUserAccount(postUser)).willReturn(mockUser);

        mockMvc.perform(post("/zcwApp/userAccount/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postUser)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$.username", is("bento")))
                .andExpect(jsonPath("$.password", is("bentoPass")))
                .andExpect(jsonPath("$.email", is("bento@gmail.com")));
    }

    @Test
    @DisplayName("PUT /userAccount/save - Fail : Username/Email exists")
    public void testSaveFailUsernameOrEmailExists() throws Exception {
        UserAccount mockUser = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                "bento", "bentoPass", "bento@gmail.com");
        given(userAccountService.hasUserEmail(mockUser)).willReturn(true);

        mockMvc.perform(post("/zcwApp/userAccount/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockUser)))

                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("PUT /userAccount/update/1 - Success")
    public void testUpdateUserSuccess() throws Exception {
        Long givenUserId = 1L;
        UserAccount updatedUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "bento", "bentoPass", "bento@gmail.com");
        given(userAccountService.updateUserAccount(givenUserId, updatedUser)).willReturn(Optional.of(updatedUser));

        mockMvc.perform(put("/zcwApp/userAccount/update/{userId}", givenUserId)
                .header(HttpHeaders.IF_MATCH, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedUser)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$.username", is("bento")))
                .andExpect(jsonPath("$.password", is("bentoPass")))
                .andExpect(jsonPath("$.email", is("bento@gmail.com")));
    }

    @Test
    @DisplayName("PUT /userAccount/update/1 - Not Found")
    public void testUpdateUserNotFound() throws Exception {
        Long givenUserId = 1L;
        UserAccount updatedUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "bento", "bentoPass", "bento@gmail.com");
        given(userAccountService.updateUserAccount(givenUserId, updatedUser)).willReturn(Optional.empty());

        mockMvc.perform(put("/zcwApp/userAccount/update/{userId}", givenUserId)
                .header(HttpHeaders.IF_MATCH, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedUser)))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /userAccount/update/1 - Fail : Username/Email exists")
    public void testUpdateFailUsernameOrEmailExists() throws Exception {
        Long givenUserId = 1L;
        UserAccount updatedUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "bento", "bentoPass", "bento@gmail.com");
        given(userAccountService.hasUserEmail(updatedUser)).willReturn(true);

        mockMvc.perform(put("/zcwApp/userAccount/update/{userId}", givenUserId)
                .header(HttpHeaders.IF_MATCH, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedUser)))

                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("DELETE /userAccount/delete/1 - Success")
    public void testDeleteUserSuccess() throws Exception {
        Long givenUserId = 1L;
        given(userAccountService.deleteUserAccountById(givenUserId)).willReturn(true);

        mockMvc.perform(delete("/zcwApp/userAccount/delete/{userId}", givenUserId))

                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /userAccount/delete/1 - Not Found")
    public void testDeleteUserNotFound() throws Exception {
        Long givenUserId = 1L;
        given(userAccountService.deleteUserAccountById(givenUserId)).willReturn(false);

        mockMvc.perform(delete("/zcwApp/userAccount/delete/{userId}", givenUserId))

                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final UserAccount obj) {
        try {
            StringBuilder jsonString = new StringBuilder("{");
            jsonString.append("\"userId\":"+obj.getUserId()+",")
                    .append("\"dateCreated\":\""+obj.getDateCreated()+"\",")
                    .append("\"username\":\""+obj.getUsername()+"\",")
                    .append("\"password\":\""+obj.getPassword()+"\",")
                    .append("\"email\":\""+obj.getEmail()+"\"}");
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}