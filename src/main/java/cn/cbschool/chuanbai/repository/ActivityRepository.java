package cn.cbschool.chuanbai.repository;

import cn.cbschool.chuanbai.entity.Activity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ActivityRepository extends BaseRepository<Activity,Integer>{

    @Query("from Activity a where a.id=:aid")
    Activity find(@Param("aid") int aid);

    @Modifying
    @Query("delete from Activity a where a.id=:aid")
     void del(@Param("aid") int aid);
}
