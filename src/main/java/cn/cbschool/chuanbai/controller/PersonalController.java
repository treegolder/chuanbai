package cn.cbschool.chuanbai.controller;

import cn.cbschool.chuanbai.entity.Activity;
import cn.cbschool.chuanbai.entity.Personal;
import cn.cbschool.chuanbai.service.PersonalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
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
    public Map register(@RequestBody Personal personal) {
        Personal p = null;
        if(personal != null) {
            p = ps.insert(personal);
            if(p == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"您输入的信息有误或已被注册，请检查后重试");
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
    @PostMapping("postactivity")
    public Map postActivity(@RequestBody Activity activity) {

        Activity a = null;
        if(activity != null) {
            Personal personal = ps.getPersonal(activity.getPersonal().getTel());
            log.debug("{}", activity.getPersonal().getTel());
            if(personal == null)
                throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"请先登录");
            activity.setPersonal(personal);
            a = ps.postActivity(activity);
            if(a == null )
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请输入完整的信息");

        }
        return Map.of("activity",a);
    }
    @GetMapping("activities")
    public Map listAll() {
        List<Activity> activities =  ps.listAll();
        log.debug("{}", activities.get(0).getName());
        return Map.of("activities",activities);
    }
    @GetMapping("activities/{aid}")
    public Map getActivity(@PathVariable int aid) {
        return Map.of("activity",ps.getActivity(aid));
    }
    @GetMapping("users/{uid}/activities")
    public Map getUserActivities(@PathVariable String uid) {
        return Map.of("userActivities",ps.getUserActivities(uid));
    }
    @PostMapping("delete/activities/{aid}")
    public void deleteActivity(@PathVariable int aid) {
        ps.deleteActivity(aid);
    }

}
