package com.agussuhardi.user.controller;

import com.agussuhardi.user.config.GlobalApiResponse;
import com.agussuhardi.user.service.UserService;
import com.agussuhardi.user.vo.UserQueryVO;
import com.agussuhardi.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UserVO request, BindingResult bindingResult) {
        userService.save(request);
        return new GlobalApiResponse<>(HttpStatus.OK.value()).entity();
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable String id) {
        userService.delete(id);
        return new GlobalApiResponse<>(HttpStatus.OK.value()).entity();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable String id) {
        return new GlobalApiResponse<>(HttpStatus.OK.value(), userService.getById(id)).entity();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(UserQueryVO vo, Pageable pageable) {
        return new GlobalApiResponse<>(HttpStatus.OK.value(), userService.query(vo, pageable)).entity();
    }
}
