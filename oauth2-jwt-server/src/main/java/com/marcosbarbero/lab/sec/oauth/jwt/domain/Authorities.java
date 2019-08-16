package com.marcosbarbero.lab.sec.oauth.jwt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MR.zt
 * @since 2019-08-16
 */
@Data
public class Authorities  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "username", type = IdType.ID_WORKER)
    private String username;

    @TableField("authority")
    private String authority;


}
