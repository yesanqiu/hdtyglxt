package com.example.hdtyglxt.entity;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ground {
    @Id
    private Integer gid;

    private String gtype;

    private String gapplication;

    private String glocation;

    /**
     * 0开放,1关闭
     */
    private String gstatus;

    private BigDecimal gcost;

    public static final String GID = "gid";

    public static final String GTYPE = "gtype";

    public static final String GAPPLICATION = "gapplication";

    public static final String GLOCATION = "glocation";

    public static final String GSTATUS = "gstatus";

    public static final String GCOST = "gcost";
}