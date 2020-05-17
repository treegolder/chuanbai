package cn.cbschool.chuanbai.controller;

import cn.cbschool.chuanbai.entity.Commodity;
import cn.cbschool.chuanbai.entity.Personal;
import cn.cbschool.chuanbai.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commodity/")
public class CommodityController {
    @Autowired
    PersonalService ps;

    @PostMapping("postCommodity")
    public Commodity postCommodity(@RequestBody Commodity commodity) {
        Personal personal = ps.getPersonal(commodity.getPersonal().getTel());
        String flag = personal.getIdentityFlag();
        //个人用户和组织无权限发布商品
        if("1".equals(flag) || "3".equals(flag))
            return null;

        commodity.setPersonal(personal);
        Commodity c = ps.postCommodity(commodity);
        return c;

    }
}
