package com.chase.play.service.impl;

import com.chase.play.dao.EvaluationMapper;
import com.chase.play.dao.ExtensionMapper;
import com.chase.play.dao.UeMapper;
import com.chase.play.dao.UserMapper;
import com.chase.play.dto.ExtensionDTO;
import com.chase.play.model.Extension;
import com.chase.play.model.Ue;
import com.chase.play.service.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 11:34
 */
@Service
public class ExtensionServiceImpl implements ExtensionService {

    @Autowired
    private ExtensionMapper extensionMapper;
    @Autowired
    private UeMapper ueMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public Extension getByEid(Integer eid) {
        // TODO 这一页中下面很多都用到了该方法下面的内容 等待用Mybatis修改
        Extension extension = extensionMapper.select(eid);
        String username = userMapper.selectByUid(extension.getUid()).getUserName();
        extension.setOriginator(username);

        // 如果出现非正整数设为1
        if (extension.getNumber() <= 0) {
            extension.setNumber(1);
            extensionMapper.setJoinNumToOne(eid);
        }

        return extension;
    }

    @Override
    public List<Extension> getByTag(Integer tag) {
        // TODO 这一页中下面很多都用到了该方法下面的内容 等待用Mybatis修改
        List<Extension> extensionList = extensionMapper.selectByTag(tag);
        List<Extension> list = new ArrayList<>();
        for (Extension extension : extensionList) {
            String username = userMapper.selectByUid(extension.getUid()).getUserName();
            extension.setOriginator(username);
            list.add(extension);
        }
        return list;
    }

    @Override
    public List<Extension> getAll() {
        List<Extension> extensionList = extensionMapper.selectAll();
        List<Extension> list = new ArrayList<>();
        for (Extension extension : extensionList) {
            String username = userMapper.selectByUid(extension.getUid()).getUserName();
            extension.setOriginator(username);
            list.add(extension);
        }
        return list;
    }

    @Override
    public int add(Extension extension) {
        int result1 = extensionMapper.add(extension);

        Ue ue = new Ue();
        ue.setEid(extension.getId());
        ue.setUid(extension.getUid());

        int result2 = ueMapper.add(ue);
        if ((result1 + result2) > 1) {
            evaluationMapper.insert(extension.getId());
            return extension.getId();
        }

        return 0;
    }

    @Override
    public boolean updateState(Integer eid) {
        int update = extensionMapper.overExtension(eid);
        return update > 0;
    }

    @Override
    public void updateMemberNumber(Integer eid) {
        extensionMapper.updateMemberNumber(eid);
    }

    @Override
    public void creaseMemberNumber(Integer eid) {
        extensionMapper.creaseMemberNumber(eid);
    }

    @Override
    public List<Extension> getByState(Integer state) {
        List<Extension> extensionList = extensionMapper.getByState(state);
        List<Extension> list = new ArrayList<>();
        for (Extension extension : extensionList) {
            String username = userMapper.selectByUid(extension.getUid()).getUserName();
            extension.setOriginator(username);
            list.add(extension);
        }
        return list;
    }

    @Override
    public List<Extension> getByUid(Integer uid) {
        List<Extension> extensionList = extensionMapper.getByUid(uid);
        List<Extension> list = new ArrayList<>();
        for (Extension extension : extensionList) {
            String username = userMapper.selectByUid(extension.getUid()).getUserName();
            extension.setOriginator(username);
            list.add(extension);
        }
        return list;
    }

    @Override
    public List<Extension> getRunningByUid(Integer uid) {
        List<Extension> extensionList = extensionMapper.getRunningByUid(uid);
        List<Extension> list = new ArrayList<>();
        for (Extension extension : extensionList) {
            String username = userMapper.selectByUid(extension.getUid()).getUserName();
            extension.setOriginator(username);
            list.add(extension);
        }
        return list;
    }

    @Override
    public List<Extension> getJoinExtensionByUid(Integer uid) {
        List<Extension> extensionList = extensionMapper.getJoinExtensionByUid(uid);
        List<Extension> list = new ArrayList<>();
        for (Extension extension : extensionList) {
            String username = userMapper.selectByUid(extension.getUid()).getUserName();
            extension.setOriginator(username);
            list.add(extension);
        }
        return list;
    }

    @Override
    public List<Extension> getOverExtension(Integer uid) {
        List<Extension> extensionList = extensionMapper.getOverByUid(uid);
        List<Extension> list = new ArrayList<>();

        for (Extension extension : extensionList) {
            String username = userMapper.selectByUid(extension.getUid()).getUserName();
            extension.setOriginator(username);
            list.add(extension);
        }
        return list;
    }

    @Override
    public boolean delete(Integer eid) {
        int result = ueMapper.deleteByEid(eid);
        if (result <= 0) {
            return false;
        }

        int delete = extensionMapper.delete(eid);
        try {
            evaluationMapper.delete(eid);
        } catch (Exception e) {
        }
        return delete > 0;
    }

    @Override
    public HashSet<Integer> getIds() throws ParseException {
        List<ExtensionDTO> list = extensionMapper.getTimeAndId();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        HashSet<Integer> ids = new HashSet<>();

        for (ExtensionDTO extensionDTO : list) {
            String[] timeData = extensionDTO.getTime().split(" ");
            Date currentDate = sdf1.parse(sdf1.format(new Date()));
            Date dateDateInfo = sdf1.parse(timeData[0]);

            if (dateDateInfo.getTime() > currentDate.getTime()) {
                continue;
            } else if (dateDateInfo.getTime() == currentDate.getTime()) {
                Date currentTimeInfo = sdf.parse(timeData[1]);
                Date currentTime = sdf.parse(sdf.format(new Date()));

                if (currentTimeInfo.getTime() <= currentTime.getTime()) {
                    ids.add(extensionDTO.getId());
                }
            } else {
                ids.add(extensionDTO.getId());
            }
        }
        return ids;
    }

    @Override
    public void stopExtension(HashSet<Integer> ids) {
        for (int id : ids) {
            extensionMapper.overExtension(id);
        }
    }


}
