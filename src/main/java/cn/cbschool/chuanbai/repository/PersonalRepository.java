package cn.cbschool.chuanbai.repository;

import cn.cbschool.chuanbai.entity.Personal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends BaseRepository<Personal,Integer>{

    @Query("select p from Personal p where p.tel=:tel")
    Personal findByTel(@Param("tel") String tel);
}
