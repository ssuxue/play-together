package com.chase.play.service.impl;

import com.chase.play.dao.UeMapper;
import com.chase.play.dao.UserMapper;
import com.chase.play.model.Ue;
import com.chase.play.model.User;
import com.chase.play.service.ExtensionService;
import com.chase.play.service.UeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 12:04
 */
@Service
public class UeServiceImpl implements UeService {

    @Autowired
    private UeMapper ueMapper;
    @Autowired
    private ExtensionService extensionService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean add(Integer uid, Integer eid) {
        Ue ue = new Ue();
        ue.setUid(uid);
        ue.setEid(eid);

        int result = ueMapper.add(ue);
        extensionService.updateMemberNumber(eid);

        return result > 0;
    }

    @Override
    public boolean deleteByUid(Integer uid) {
        int delete = ueMapper.deleteByUid(uid);
        return delete > 0;
    }

    @Override
    public boolean deleteByEid(Integer eid) {
        int delete = ueMapper.deleteByEid(eid);
        return delete > 0;
    }

    @Override
    public boolean exitExtension(Integer uid, Integer eid) {
        int result = ueMapper.delete(uid, eid);
        extensionService.creaseMemberNumber(eid);
        return result > 0;
    }

    @Override
    public List<User> getAttendUser(Integer eid) {
        return ueMapper.getAttendUser(eid);
    }
}
