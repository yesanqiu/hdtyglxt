package com.example.hdtyglxt.service.impl;

import com.example.hdtyglxt.base.service.impl.BaseServiceImpl;
import com.example.hdtyglxt.dto.GroundBookDTO;
import com.example.hdtyglxt.entity.GroundBook;
import com.example.hdtyglxt.mapper.GroundBookMapper;
import com.example.hdtyglxt.service.GroundBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroundBookServiceImpl extends BaseServiceImpl<GroundBookMapper, GroundBook> implements GroundBookService {
    @Autowired
    private GroundBookMapper groundBookMapper;
    @Override
    public List<GroundBookDTO> querymybook(Integer uid) {
        return groundBookMapper.querymybook(uid);
    }

    @Override
    public List<GroundBook> querybookByid(Integer userid) throws Exception {
        return groundBookMapper.querybookByid(userid);
    }
}
