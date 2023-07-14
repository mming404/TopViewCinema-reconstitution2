package com.ysm.www.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Data
public class FilmConditionBo {

    private String filmName;

    private String filmType;

    private LocalDate startDate;

    @NotNull(message = "页码不能为空")
    private Integer pageNum;
}
