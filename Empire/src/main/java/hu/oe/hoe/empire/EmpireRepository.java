package hu.oe.hoe.empire;

import hu.oe.hoe.model.Empire;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpireRepository extends JpaRepository<Empire, Long> {

    
  @Query("select s from Empire s where s.userid = :userid order by s.name asc")
  public List<Empire> findByUseridOrderNameAsc(@Param("userid")String pUserid);    

  @Query("select s from Empire s where s.id = :id and s.userid = :userid")
  public Empire findByIdAndUserid(@Param("id")long pId, @Param("userid")String pUserid);    

  @Query("select s from Empire s where s.name = :name and s.userid = :userid")
  public Empire findByNameAndUserid(@Param("name")String pName, @Param("userid")String pUserid);    
  
  @Modifying
  @org.springframework.transaction.annotation.Transactional
  @Query("UPDATE Empire s SET s.name = :name, s.description = :description WHERE s.id =:id and s.userid = :userid")
  int modify(
          @Param("name")String pName, 
          @Param("description")String pDescription, 
          @Param("id") long pId,
          @Param("userid")String pUserid);
}

