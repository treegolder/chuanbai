package cn.cbschool.chuanbai.service;

import cn.cbschool.chuanbai.entity.Activity;
import cn.cbschool.chuanbai.entity.Commodity;
import cn.cbschool.chuanbai.entity.Personal;
import cn.cbschool.chuanbai.repository.ActivityRepository;
import cn.cbschool.chuanbai.repository.CommodityRepository;
import cn.cbschool.chuanbai.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PersonalService {
    @Autowired
    PersonalRepository pr;
    @Autowired
    CommodityRepository cr;
    @Autowired
    ActivityRepository ar;

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

               if(personal.getSchoolNumber() == null || !personal.getSchool().equals("东北林业大学"))
                   return null;
                personal.setSchool(personal.getSchool());
               personal.setSchoolNumber(personal.getSchoolNumber());
            }
            if("2".equals(personal.getIdentityFlag())) {
                if(personal.getIdentity() == null || personal.getIdentity().length() != 18)
                    return null;
                personal.setIdentity(personal.getIdentity());
                personal.setCompanyName(personal.getCompanyName());
            }
            if("3".equals(personal.getIdentityFlag())) {
                personal.setCompanyName(personal.getCompanyName());
                personal.setSchool(personal.getSchool());
            }
            pr.save(personal);
            return personal;

    }


    public Personal getPersonal(String tel) {
        return pr.findByTel(tel);
    }

    public Activity postActivity(Activity activity) {
        if(activity.getName() == null || activity.getDate() == null || activity.getPlace() == null) return null;
        if (activity.getPersonal() == null) return null;
        return ar.save(activity);
    }
    public List<Activity> listAll() {
       return ar.findAll();
    }
    public Activity getActivity(int aid) {
        return ar.find(aid);
    }
    public List<Activity> getUserActivities(String uid) {
        return pr.findByTel(uid).getActivities();
    }
    @Transactional
    public void deleteActivity(int aid) {
         ar.del(aid);
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
