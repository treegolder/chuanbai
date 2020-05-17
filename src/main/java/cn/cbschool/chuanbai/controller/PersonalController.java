package cn.cbschool.chuanbai.controller;

import cn.cbschool.chuanbai.entity.Personal;
import cn.cbschool.chuanbai.service.PersonalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;



@Slf4j
@RestController
@RequestMapping("/api/")
@Validated
public class PersonalController {
    @Autowired
    PersonalService ps;
    @PostMapping("register")
    public Map register(@Valid @RequestBody Personal personal) {
        Personal p = null;
        if(personal != null) {
            p = ps.insert(personal);
            if(p == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"您输入的信息有误或已被注册，请检查");
        }
        return Map.of("personal",p);
    }
    @PostMapping("login")
    public Map login(@Valid@RequestBody Personal login) {
       Personal personal = Optional.ofNullable(ps.getPersonal(login.getTel()))
               .filter(p -> login.getPwd().equals(p.getPwd()))
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名密码错误"));
        log.debug("{}",personal.getIdentityFlag());
       return Map.of("personal",personal);
    }
}
