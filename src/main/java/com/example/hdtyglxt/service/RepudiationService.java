package com.example.hdtyglxt.service;

import com.example.hdtyglxt.base.service.BaseService;

import com.example.hdtyglxt.dto.BlackDTO;
import com.example.hdtyglxt.entity.TbRepudiation;

import java.util.List;

public interface RepudiationService extends BaseService<TbRepudiation> {
    List<BlackDTO> listblack()throws Exception;
}
