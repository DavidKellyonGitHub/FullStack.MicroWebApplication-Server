package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.UserAccount;
import com.dmvs.blog.projBlog.Repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {

    private UserAccountRepository userAccountRepo;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepo){
        this.userAccountRepo = userAccountRepo;
    }

    public Optional<UserAccount> findById(Long userId){
        return userAccountRepo.findById(userId);
    }

    public List<UserAccount> findAllUser(){
        return userAccountRepo.findAll();
    }

    public UserAccount saveUserAccount(UserAccount userAccount){
        return userAccountRepo.save(userAccount);
    }

    public Optional<UserAccount> updateUserAccount(Long userId, UserAccount newUserAccount){
        Optional<UserAccount> currentUserAccount = findById(userId);
        if(currentUserAccount.isPresent()){
            currentUserAccount.get().setUsername(newUserAccount.getUsername());
            currentUserAccount.get().setPassword(newUserAccount.getPassword());
            currentUserAccount.get().setEmail(newUserAccount.getEmail());
            userAccountRepo.save(currentUserAccount.get());
        }
        return currentUserAccount;
    }

    public Boolean deleteUserAccountById(Long userId){
        Optional<UserAccount> currentUserAccount = findById(userId);
        if(currentUserAccount.isPresent()){
            userAccountRepo.deleteById(userId);
            return true;
        }
        return false;
    }
}
