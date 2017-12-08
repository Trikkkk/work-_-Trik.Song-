package com.trik.service;

import com.trik.entity.MsgEntity;

import java.util.List;

/**
 * Created by Trik.Song on 2017/11/29.
 */
public interface serviceInterface {
    public List<MsgEntity> getMsgEntityList();

    public MsgEntity findMsgEntityById(long id);

    public void save(MsgEntity messageEntity);

    public void delete(long id);
}

