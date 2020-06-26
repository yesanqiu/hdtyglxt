package com.example.hdtyglxt.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "tb_notice")
@NoArgsConstructor
@AllArgsConstructor
public class TbNotice {
    @Id
    private Integer nid;

    private String nnotice;

    public static final String NID = "nid";

    public static final String NNOTICE = "nnotice";
}