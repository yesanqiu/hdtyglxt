package com.example.hdtyglxt.dto;

import com.example.hdtyglxt.base.dto.BaseDTO;
import com.example.hdtyglxt.entity.Ground;
import com.example.hdtyglxt.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroundBookDTO extends BaseDTO {
    private Integer gGid;
    private Integer bid;
    private Date bstartTime;
    private Date bendTime;
    private BigDecimal bcost;
    private String bisuse;
    private Integer userid;
    private Ground ground;
    private User user;
}
