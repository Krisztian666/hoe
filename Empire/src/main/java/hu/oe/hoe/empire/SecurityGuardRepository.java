package hu.oe.hoe.empire;

import hu.oe.hoe.model.Empire;
import hu.oe.hoe.model.SecurityGuard;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecurityGuardRepository extends JpaRepository<SecurityGuard, Long> {
    
  @Query("select s from SecurityGuard s where s.empire = :empire and s.starttime <= :starttime and s.stoptime >= :stoptime order by s.starttime asc")
  public List<SecurityGuard> findByEmpireStarttimeStoptime(@Param("empire")Empire pEmpire, @Param("starttime")Date pStarttime, @Param("stoptime")Date pStoptime );    

}
