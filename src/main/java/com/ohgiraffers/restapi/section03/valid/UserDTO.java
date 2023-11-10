package com.ohgiraffers.restapi.section03.valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private int no;

    @NotNull(message = "아이디는 반드시 입력 되어야 합니다.") // null x, "", "  " o
    @NotBlank(message = "아이디는 공백일 수 없습니다.")      // null, "", "  " x
    private String id;
    private String pwd;

    @NotNull(message = "이름은 반드시 입력 되어야 합니다.")
    @Size(min=2, message="이름은 2글자 이상이어야 합니다.")  // min=, max=   /   @Min, @Max
    private String name;

    @Past   // @Past, @Future 지금 시스템 시간보다 과거시간, 미래시간이 들어가야 한다.
    private Date enrollDate;
}
