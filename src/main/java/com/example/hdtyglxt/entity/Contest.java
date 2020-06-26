package com.example.hdtyglxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "contest")
@NoArgsConstructor
@AllArgsConstructor
public class Contest {

    @Id
    private Long cId;

    private String cName;

    private String cApply;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date cStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date cEndDate;

    private String cGdType;

}
