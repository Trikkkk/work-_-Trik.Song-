package com.trik.dao;

import com.trik.entity.MsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Trik.Song on 2017/11/27
 */


public interface MsgDao extends JpaRepository<MsgEntity,Long> {
             MsgEntity findById(long id);
}
