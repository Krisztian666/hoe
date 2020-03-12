package hu.oe.hoe.speciesandendowments;

import hu.oe.hoe.model.Species;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SpeciesRepository extends JpaRepository<Species, Long>{
    
  @Query("select s from Species s order by s.name asc")
  public List<Species> findOrderNameAsc();    

  @Query("select s from Species s where s.id = :id")
  public Species findById(@Param("id")long pId);    

  @Query("select s from Species s where s.name = :name")
  public Species findByName(@Param("name")String pName);    
  
  @Modifying
  @Transactional
  @Query("UPDATE Species s SET s.name = :name, s.description = :description WHERE s.id =:id")
  int modify(@Param("name")String pName, @Param("description")String pDescription, @Param("id") long pId);

  
}