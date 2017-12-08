package com.trik.service;

import com.trik.dao.MsgDao;
import com.trik.entity.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Created by Trik.Song on 2017/11/29.
 */

@Service
public class service implements serviceInterface   {
    @Autowired
    private MsgDao msgDao;

    @Override
    public List<MsgEntity> getMsgEntityList(){
        return msgDao.findAll();
    }
    @Override
    public MsgEntity findMsgEntityById(long id) {
        return msgDao.findById(id);
    }
    @Override
    public void save(MsgEntity msgEntity){ msgDao.save(msgEntity);}
    @Override
    public void delete(long id){msgDao.delete(id);}




}

