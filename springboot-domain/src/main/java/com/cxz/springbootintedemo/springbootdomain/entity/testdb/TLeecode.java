package com.cxz.springbootintedemo.springbootdomain.entity.testdb;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.Data;

/**
 * 
 *
 * @author cxz
 * @since 2021-09-01
 */
@Data
@TableName("T_LEECODE")
public class TLeecode implements Serializable{

    private static final long serialVersionUID=1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Long Id;

    @TableField("translatedTitle")
    private String translatedtitle;

    @TableField("frontendId")
    private String frontendid;

    @TableField("titleSlug")
    private String titleslug;

    private String title;

    private String difficulty;

    @TableField("lastSubmittedAt")
    private String lastsubmittedat;

    @TableField("numSubmitted")
    private Integer numsubmitted;

    @TableField("lastSubmissionSrc")
    private String lastsubmissionsrc;
    @TableField("typename")
    private String typename;
    @TableField("createTime")
    private LocalDateTime createtime;
}
