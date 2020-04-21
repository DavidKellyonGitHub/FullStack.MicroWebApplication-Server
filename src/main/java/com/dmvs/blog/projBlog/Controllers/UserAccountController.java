package com.dmvs.blog.projBlog.Controllers;

import com.dmvs.blog.projBlog.Models.UserAccount;
import com.dmvs.blog.projBlog.Services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/zcwApp/userAccount")
@CrossOrigin
public class UserAccountController {

    private UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable Long userId) {
        return userAccountService.findById(userId)
                .map(userAccount -> ResponseEntity
                        .ok()
                        .body(userAccount))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/login")
    public ResponseEntity<?> findByUserPass(@RequestParam String username, @RequestParam String password){
        return userAccountService.findByUserPass(username, password)
                .map(userAccount -> ResponseEntity
                        .ok()
                        .body(userAccount))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<UserAccount>> findAllUser() {
        return new ResponseEntity<>(userAccountService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserAccount> saveUserAccount(@RequestBody UserAccount userAccount) {
        if(userAccountService.hasUserEmail(userAccount))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        UserAccount newUserAccount = userAccountService.saveUserAccount(userAccount);

        try {
            return ResponseEntity
                    .created(new URI("/zcwApp/userAccount/" + newUserAccount.getUserId()))
                    .body(newUserAccount);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUserAccount(@PathVariable Long userId, @RequestBody UserAccount newUserAccount) {
        if(userAccountService.hasUserEmail(newUserAccount))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        Optional<UserAccount> updatedUserAccount = userAccountService.updateUserAccount(userId, newUserAccount);

        return updatedUserAccount
                .map(userAccount -> {
                    try{
                        return ResponseEntity
                                .ok()
                                .location(new URI("/zcwApp/userAccount/" + userAccount.getUserId()))
                                .body(userAccount);
                    }catch(URISyntaxException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long userId) {
        if(userAccountService.deleteUserAccountById(userId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}
