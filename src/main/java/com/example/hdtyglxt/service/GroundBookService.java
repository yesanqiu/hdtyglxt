package com.example.hdtyglxt.service;

import com.example.hdtyglxt.base.service.BaseService;

import com.example.hdtyglxt.dto.GroundBookDTO;
import com.example.hdtyglxt.entity.GroundBook;

import java.util.List;

public interface GroundBookService extends BaseService<GroundBook> {
    List<GroundBookDTO> querymybook(Integer userid) throws Exception;
    List<GroundBook> querybookByid(Integer userid) throws Exception;
}
