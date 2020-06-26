package com.example.hdtyglxt.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "gb_time")
@NoArgsConstructor
@AllArgsConstructor
public class GbTime {
    @Id
    private Integer gbid;

    @Column(name = "gb_gid")
    private Integer gbGid;

    private String starttime;

    private String endtime;

    private String gbstatus;

    public static final String GBID = "gbid";

    public static final String GB_GID = "gbGid";

    public static final String STARTTIME = "starttime";

    public static final String ENDTIME = "endtime";

    public static final String GBSTATUS = "gbstatus";
}