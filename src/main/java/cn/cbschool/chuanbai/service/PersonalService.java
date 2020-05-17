package cn.cbschool.chuanbai.service;

import cn.cbschool.chuanbai.entity.Commodity;
import cn.cbschool.chuanbai.entity.Personal;
import cn.cbschool.chuanbai.repository.CommodityRepository;
import cn.cbschool.chuanbai.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class PersonalService {
    @Autowired
    PersonalRepository pr;
    @Autowired
    CommodityRepository cr;


    /**
     * 注册
     * @param personal
     * @return
     */
    public Personal insert(Personal personal) {
            //重复注册返回空对象
            if (pr.findByTel(personal.getTel()) != null)
                 return null;
            if("1".equals(personal.getIdentityFlag())) {
               personal.setSchool(personal.getSchool());
            }
            if("2".equals(personal.getIdentityFlag())) {
                personal.setStoreName(personal.getStoreName());
            }
            if("3".equals(personal.getIdentityFlag())) {
                personal.setStoreName(personal.getStoreName());
                personal.setSchool(personal.getSchool());
            }
            pr.save(personal);
            return personal;

    }


    public Personal getPersonal(String tel) {
        return pr.findByTel(tel);
    }



    /**
     * 发布商品
     * @return
     */
    public Commodity postCommodity(Commodity commodity) {
      return cr.save(commodity);
    }

    /**
     * 交易
     * @return
     */
    public int purchasing(Personal personal, Commodity commodity) {

        return 0;
    }
}
