package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.UserAccount;
import com.dmvs.blog.projBlog.Repositories.UserAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("h2")
public class UserAccountServiceTest {

    @Autowired
    UserAccountService userAccountService;

    @MockBean
    UserAccountRepository userAccountRepository;

    @Test
    @DisplayName("Test findById - Found")
    public void testFindByIdFound(){
        Long givenUserId = 1L;
        UserAccount mockUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        given(userAccountRepository.findById(givenUserId)).willReturn(Optional.of(mockUser));

        Optional<UserAccount> returnUserAccount = userAccountService.findById(givenUserId);

        assertTrue(returnUserAccount.isPresent());
        assertSame(returnUserAccount.get(), mockUser);
    }

    @Test
    @DisplayName("Test findById - Not Found")
    public void testFindByIdNotFound() {
        Long givenUserId = 1L;
        given(userAccountRepository.findById(givenUserId)).willReturn(Optional.empty());

        Optional<UserAccount> returnUserAccount = userAccountService.findById(givenUserId);

        assertFalse(returnUserAccount.isPresent());
    }

    @Test
    @DisplayName("Test findAllUsers - Found")
    public void testFindAllUsers(){
        UserAccount user1 = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        UserAccount user2 = new UserAccount(2L, LocalDate.of(2015, 4, 9),
                "Horatio", "horaPass", "hora@gmail.com");
        UserAccount user3 = new UserAccount(3L, LocalDate.of(2015, 4, 9),
                "Hamlet", "hamPass", "hama@gmail.com");
        List<UserAccount> mockUserList = new ArrayList<>(Arrays.asList(user1, user2, user3));
        given(userAccountRepository.findAll()).willReturn(mockUserList);

        List<UserAccount> returnUserList = userAccountService.findAllUsers();

        assertEquals(3, returnUserList.size());
        assertTrue(returnUserList.contains(user1));
        assertTrue(returnUserList.contains(user2));
        assertTrue(returnUserList.contains(user3));
    }

    @Test
    @DisplayName("Test save - Success")
    public void testSave() {
        String givenUsername = "Rosalind";
        String givenEmail = "rosa@gmail.com";
        UserAccount mockUser = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                givenUsername, "rosaPass", givenEmail);
        given(userAccountRepository.existsByUsername(givenEmail)).willReturn(false);
        given(userAccountRepository.existsByUsername(givenUsername)).willReturn(false);
        given(userAccountRepository.save(mockUser)).willReturn(mockUser);

        UserAccount returnUserAccount = userAccountService.saveUserAccount(mockUser);

        assertEquals(mockUser, returnUserAccount);
    }

    @Test
    @DisplayName("Test save - Fail : Username exists")
    public void testSaveFailUsernameExists(){
        String givenUsername = "Rosalind";
        UserAccount mockUser = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                givenUsername, "rosaPass", "rosa@gmail.com");
        given(userAccountRepository.existsByUsername(givenUsername)).willReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userAccountService.saveUserAccount(mockUser));
    }

    @Test
    @DisplayName("Test save - Fail : Email exists")
    public void testSaveFailEmailExists(){
        String givenEmail = "rosa@gmail.com";
        UserAccount mockUser = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", givenEmail);
        given(userAccountRepository.existsByEmail(givenEmail)).willReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userAccountService.saveUserAccount(mockUser));
    }

    @Test
    @DisplayName("Test update - Success")
    public void testUpdateSuccess(){
        Long givenUserId = 1L;
        UserAccount beforeMockUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        UserAccount updatedUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "bento", "bentoPass", "bento@gmail.com");
        given(userAccountRepository.findById(givenUserId)).willReturn(Optional.of(beforeMockUser));
        given(userAccountRepository.save(beforeMockUser)).willReturn(updatedUser);

        Optional<UserAccount> returnUserAccount = userAccountService.updateUserAccount(givenUserId, updatedUser);

        assertEquals(updatedUser, returnUserAccount.get());
    }

    @Test
    @DisplayName("Test update - Not Found")
    public void testUpdateNotFound(){
        Long givenUserId = 1L;
        UserAccount updatedUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "bento", "bentoPass", "bento@gmail.com");
        given(userAccountRepository.findById(givenUserId)).willReturn(Optional.empty());

        Optional<UserAccount> returnUserAccount = userAccountService.updateUserAccount(givenUserId, updatedUser);

        assertFalse(returnUserAccount.isPresent());
    }

    @Test
    @DisplayName("Test update - Fail : Username exists")
    public void testUpdateFailUsernameExists(){
        Long givenUserId = 1L;
        String givenUsername = "Rosalind";
        UserAccount mockUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                givenUsername, "rosaPass", "rosa@gmail.com");
        given(userAccountRepository.existsByUsername(givenUsername)).willReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userAccountService.updateUserAccount(givenUserId, mockUser));
    }

    @Test
    @DisplayName("Test update - Fail : Email exists")
    public void testUpdateFailEmailExists(){
        Long givenUserId = 1L;
        String givenEmail = "rosa@gmail.com";
        UserAccount mockUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", givenEmail);
        given(userAccountRepository.existsByEmail(givenEmail)).willReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userAccountService.updateUserAccount(givenUserId, mockUser));
    }

    @Test
    @DisplayName("Test update - Found and deleted")
    public void testDeleteUserFound() {
        Long givenUserId = 1L;
        UserAccount mockUser = new UserAccount(givenUserId, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        given(userAccountRepository.findById(givenUserId)).willReturn(Optional.of(mockUser));

        Boolean returnBoolean = userAccountService.deleteUserAccountById(givenUserId);

        verify(userAccountRepository, times(1)).deleteById(givenUserId);
        assertTrue(returnBoolean);
    }

    @Test
    @DisplayName("Test update - Not Found")
    public void testDeleteCommentNotFound(){
        Long givenUsesId = 1L;
        given(userAccountRepository.findById(givenUsesId)).willReturn(Optional.empty());

        Boolean returnBoolean = userAccountService.deleteUserAccountById(givenUsesId);

        verify(userAccountRepository, times(0)).deleteById(givenUsesId);
        assertFalse(returnBoolean);
    }
}