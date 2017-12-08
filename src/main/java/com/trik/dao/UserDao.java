package com.trik.dao;

import com.trik.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Trik.Song on 2017/11/27
 */

@Repository
public interface UserDao extends CrudRepository<UserEntity,Long>{
    public UserEntity findByUsernameAndPassword(String username,String password);
    public UserEntity findByUsername(String username);

}
