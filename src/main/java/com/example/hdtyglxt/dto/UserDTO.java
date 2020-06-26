package com.example.hdtyglxt.dto;

import com.example.hdtyglxt.base.dto.BaseDTO;
import com.example.hdtyglxt.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    private String username;

    private String nickname;

    private String studentId;

    private Integer role;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.studentId = user.getStudentId();
        this.role = user.getRole();
    }
}
