package hu.oe.hoe.heroes;

import hu.oe.hoe.model.Hero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface HeroRepository extends JpaRepository<Hero, Long>{
    
  @Query("select s from Hero s where s.userid = :userid order by s.name asc")
  public List<Hero> findByUseridOrderNameAsc(@Param("userid")String pUserid);    

  @Query("select s from Hero s where s.id = :id and s.userid = :userid")
  public Hero findByIdAndUserid(@Param("id")long pId, @Param("userid")String pUserid);    

  @Query("select s from Hero s where s.name = :name and s.userid = :userid")
  public Hero findByNameAndUserid(@Param("name")String pName, @Param("userid")String pUserid);    
  
  @Modifying
  @Transactional
  @Query("UPDATE Hero s SET s.name = :name, s.description = :description WHERE s.id =:id and s.userid = :userid")
  int modify(
          @Param("name")String pName, 
          @Param("description")String pDescription, 
          @Param("id") long pId,
          @Param("userid")String pUserid);
}