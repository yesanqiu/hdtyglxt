package com.example.hdtyglxt.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "ground_book")
@NoArgsConstructor
@AllArgsConstructor
public class GroundBook {
    @Column(name = "g_gid")
    private Integer gGid;
    @Id
    private Integer bid;

    @Column(name = "bstart_time")
    private Date bstartTime;

    @Column(name = "bend_time")
    private Date bendTime;

    private Integer userid;

    private BigDecimal bcost;

    /**
     * 0未使用，1使用
     */
    private String bisuse;

    public static final String G_GID = "gGid";

    public static final String BID = "bid";

    public static final String BSTART_TIME = "bstartTime";

    public static final String BEND_TIME = "bendTime";

    public static final String USERID = "userid";

    public static final String BCOST = "bcost";

    public static final String BISUSE = "bisuse";
}