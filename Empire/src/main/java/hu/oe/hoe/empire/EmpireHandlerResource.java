package hu.oe.hoe.empire;

import hu.oe.hoe.base.DataException;
import hu.oe.hoe.model.Ability;
import hu.oe.hoe.model.Empire;
import hu.oe.hoe.model.Hero;
import hu.oe.hoe.model.Hybrid;
import hu.oe.hoe.model.SecurityGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@CrossOrigin("*")
@RequestMapping(path = {"/empirehandler"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpireHandlerResource{
    @Autowired
    private EmpireRepository repositoryEmpire;

    @Autowired
    private SecurityGuardRepository repositorySecurity;
    
    @Value( "${service.hero}" )
    private String serviceHeroUrl;
    
    @Operation(
        description = "Új birodalom felvitele.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás felvitel")
        })
    @RolesAllowed("user")    
    @PostMapping(produces = "application/json")
    public @ResponseBody Collection<Empire> addEmpire(Principal pSc, @RequestBody Empire pData){
        pData.setUserid(pSc.getName());
        repositoryEmpire.save(pData);
        return repositoryEmpire.findByUseridOrderNameAsc(pData.getUserid());
    }
   
    @Operation(
        description = "Birodalom lista lekérdezése.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
        })
    @GetMapping(path="/getallempires", produces = "application/json")
    public @ResponseBody List<Empire> getAllEmpires(){
        return repositoryEmpire.findAll(Sort.by(Empire.Fields.name));
    }

    @Operation(
        description = "Birodalom lista lekérdezése.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
        })
    @RolesAllowed("user")     
    @GetMapping(path="/getallmyempires", produces = "application/json")
    public @ResponseBody List<Empire> getAllMyEmpires(Principal principal){
        return repositoryEmpire.findByUseridOrderNameAsc(principal.getName());
    }

    @Operation(
        description = "Egy Birodalom lekérdezése.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
        })
    @RolesAllowed("user") 
    @GetMapping(path ="/byname/{name}", produces = "application/json")
    public @ResponseBody Empire getEmpireByName(Principal principal, @PathVariable String name){
        return repositoryEmpire.findByNameAndUserid(name, principal.getName());
    }

     @Operation(
        description = "Egy Birodalom lekérdezése.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
        })
    @GetMapping(path ="/byid/{id}", produces = "application/json")
    public @ResponseBody Empire getEmpireById(@PathVariable(name = "id") Long pId){
        return repositoryEmpire.findById(pId).get();
    }
    
     @Operation(
        description = "Egy Birodalom lekérdezése.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
        })
    @DeleteMapping(path ="/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteEmpireById(@PathVariable(name = "id") Long pId){
        repositoryEmpire.deleteById(pId);
        return true;
    }
    


    @Operation(
        description = "Egy Birodalom védelmének megszervezése",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "404", description = "Nincs ilyen azonosítóju hős")
    })
    @RolesAllowed("user") 
    @PostMapping(path ="/security", produces = "application/json")
    public @ResponseBody Empire setSecurityGuard(Principal pSc, @RequestBody SecurityGuard pData){
        String uri = "";
        Empire tmp = repositoryEmpire.findByIdAndUserid(pData.getEmpireid(),pSc.getName());
        if(tmp!=null){
            pData.setEmpire(tmp);
            repositorySecurity.save(pData);
            tmp.setProtect(pData);
            repositoryEmpire.save(tmp);
            return tmp;
        }
        throw new DataException();
    } 
  
    @Operation(
        description = "Egy Birodalom védelmének lekérdezése",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "404", description = "Nincs ilyen azonosítóju hős")
    })
    @GetMapping(path ="/security", produces = "application/json")
    public @ResponseBody List<SecurityGuard> getSecurityGuard(Principal pSc, @RequestParam Long id, @RequestParam Date starttime, @RequestParam Date stoptime ){
        Empire tmp = repositoryEmpire.findByIdAndUserid(id,pSc.getName());
        if(tmp!=null){
            return repositorySecurity.findByEmpireStarttimeStoptime(tmp, starttime, stoptime);
        }
        throw new DataException();
    } 
    
    @Operation(
        description = "Egy Birodalom megtámadása",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "404", description = "Vesztes csata")
    })
    @GetMapping(path ="/attack/{empireid}/{heroid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean attack(Principal pSc, @PathVariable(name = "empireid") Long empireid, @PathVariable(name = "heroid") Long heroid){
            String uri = serviceHeroUrl+"/hero/byid/"+heroid;
            RestTemplate restTemplate = new RestTemplate();
            Hero protect = restTemplate.getForObject(uri, Hero.class);
            
            uri = serviceHeroUrl+"/hero/byid/"+heroid;
            Hero attact = restTemplate.getForObject(uri, Hero.class);
            
            return getScore(attact)>getScore(protect);
            
    } 
     
 
    private float getScore(Hero protect){
           float protScore = 0;
            Iterator<Hybrid> itHybrid = protect.getHybrid().iterator();
            Hybrid hybrid;
            while (itHybrid.hasNext()){
                hybrid = itHybrid.next();
                for(Ability abl: hybrid.getSpecies().getEndowments()){
                    protScore+= hybrid.getPercent()*(abl.getPower()+abl.getBrain()+abl.getSkill());
                }
            }
     
            return protScore;
    }
    
}
