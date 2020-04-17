package com.dmvs.blog.projBlog.Repositories;

import com.dmvs.blog.projBlog.Models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
