package com.example.hdtyglxt.mapper;

import com.example.hdtyglxt.dto.BlackDTO;
import com.example.hdtyglxt.entity.TbRepudiation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbRepudiationMapper extends Mapper<TbRepudiation> {
    List<BlackDTO> listblack();
}