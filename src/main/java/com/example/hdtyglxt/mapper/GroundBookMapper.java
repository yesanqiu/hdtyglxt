package com.example.hdtyglxt.mapper;

import com.example.hdtyglxt.dto.GroundBookDTO;
import com.example.hdtyglxt.entity.GroundBook;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GroundBookMapper extends Mapper<GroundBook> {
    List<GroundBookDTO> querymybook(Integer userid);
    List<GroundBook> querybookByid(Integer userid);
}