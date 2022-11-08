package com.agussuhardi.user.service.impl;

import com.agussuhardi.user.config.exception.CustomException;
import com.agussuhardi.user.dto.UserDTO;
import com.agussuhardi.user.entity.User;
import com.agussuhardi.user.repository.UserRepository;
import com.agussuhardi.user.service.UserService;
import com.agussuhardi.user.vo.UserQueryVO;
import com.agussuhardi.user.vo.UserVO;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public void save(UserVO vO) {
        User bean = new User();
        if (!Strings.isNullOrEmpty(vO.getId()))
            bean = userRepository.findById(vO.getId()).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        BeanUtils.copyProperties(vO, bean, User.Fields.id);
        userRepository.save(bean);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getById(String id) {
        User original = requireOne(id);
        return toDTO(original);
    }

    @Override
    public Page<UserDTO> query(UserQueryVO vO, Pageable pageable) {
        return userRepository.findAll(pageable).map(this::toDTO);
    }

    private UserDTO toDTO(User original) {
        UserDTO bean = new UserDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private User requireOne(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
