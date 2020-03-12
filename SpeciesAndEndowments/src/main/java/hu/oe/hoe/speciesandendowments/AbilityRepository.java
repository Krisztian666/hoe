package hu.oe.hoe.speciesandendowments;

import hu.oe.hoe.model.Ability;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AbilityRepository extends JpaRepository<Ability, Long> {

    @Query("select s from Ability s order by s.name asc")
    public List<Ability> findOrderNameAsc();

    @Query("select s from Ability s where s.id = :id")
    public Ability findById(@Param("id") long pId);

    @Query("select s from Ability s where s.name = :name")
    public Ability findByName(@Param("name") String pName);

    @Modifying
    @Transactional
    @Query("UPDATE Ability s "
            + "SET s.name = :name, s.description = :description, s.skill = :skill, s.brain= :brain, s.power = :power "
            + "WHERE s.id =:id")
    int modify(
            @Param("name") String pName,
            @Param("description") String pDescription,
            @Param("skill") byte pSkill,
            @Param("brain") byte pBrain,
            @Param("power") byte pPower,
            @Param("id") long pId);
}
