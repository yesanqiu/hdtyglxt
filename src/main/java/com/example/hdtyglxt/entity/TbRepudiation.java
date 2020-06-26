package com.example.hdtyglxt.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_repudiation")
public class TbRepudiation {
    @Id
    private Integer rid;

    @Column(name = "r_userid")
    private Integer rUserid;

    public static final String RID = "rid";

    public static final String R_USERID = "rUserid";
}