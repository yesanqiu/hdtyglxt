package com.example.hdtyglxt.dto;


import com.example.hdtyglxt.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlackDTO {
    @Id
    private Integer rid;
    private Integer rUserid;
    private User user;
}
