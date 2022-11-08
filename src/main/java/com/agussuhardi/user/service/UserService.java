package com.agussuhardi.user.service;

import com.agussuhardi.user.dto.UserDTO;
import com.agussuhardi.user.vo.UserQueryVO;
import com.agussuhardi.user.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void save(UserVO vO);

    void delete(String id);

    UserDTO getById(String id);

    Page<UserDTO> query(UserQueryVO vO, Pageable pageable);
}
