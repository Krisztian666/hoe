package hu.oe.hoe.speciesandendowments;

import hu.oe.hoe.model.Ability;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbilityService {
    
    private final AbilityRepository eRepository;

    @Autowired
    public AbilityService(AbilityRepository eRepository) {
        this.eRepository = eRepository;
    }
    
    public Collection<Ability> getAll(){return this.eRepository.findOrderNameAsc();}
    
    public void update(long pID, Ability pData){
        this.eRepository.modify(pData.getName(), pData.getDescription(), pData.getSkill(), pData.getBrain(), pData.getPower(), pID);
    }
 
    public void save(Ability pData){this.eRepository.save(pData);}
    
}
