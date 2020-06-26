package com.example.hdtyglxt.service.impl;

import com.example.hdtyglxt.base.service.impl.BaseServiceImpl;
import com.example.hdtyglxt.dto.BlackDTO;
import com.example.hdtyglxt.entity.TbRepudiation;
import com.example.hdtyglxt.mapper.TbRepudiationMapper;
import com.example.hdtyglxt.service.RepudiationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepudiationServiceImpl extends BaseServiceImpl<TbRepudiationMapper, TbRepudiation> implements RepudiationService {
    @Autowired
    private TbRepudiationMapper tbRepudiationMapper;
    @Override
    public List<BlackDTO> listblack() throws Exception {
        return tbRepudiationMapper.listblack();
    }
}
