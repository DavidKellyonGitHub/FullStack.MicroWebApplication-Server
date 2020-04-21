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

    public Optional<UserAccount> findByUserPass(String username, String password){
        return userAccountRepo.findByUsernameAndPassword(username, password);
    }

    public List<UserAccount> findAllUsers(){
        return userAccountRepo.findAll();
    }

    public UserAccount saveUserAccount(UserAccount newUserAccount) throws IllegalArgumentException{
        return userAccountRepo.save(newUserAccount);

    }

    public Optional<UserAccount> updateUserAccount(Long userId, UserAccount newUserAccount)throws IllegalArgumentException{
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

    public Boolean hasUserEmail(UserAccount userAccount){
        if(userAccountRepo.existsByUsername(userAccount.getUsername()) ||
            userAccountRepo.existsByEmail(userAccount.getEmail()))
            return true;
        return false;
    }
}
