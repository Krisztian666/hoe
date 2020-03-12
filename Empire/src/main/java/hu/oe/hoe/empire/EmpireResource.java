package hu.oe.hoe.empire;

import hu.oe.hoe.base.DataException;
import hu.oe.hoe.model.Empire;
import hu.oe.hoe.model.Hero;
import hu.oe.hoe.model.Security;
import hu.oe.hoe.model.Species;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@CrossOrigin("*")
@RequestMapping(path = {"/empire"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpireResource{
    @Autowired
    private EmpireRepository repositoryEmpire;

    @Autowired
    private SecurityRepository repositorySecurity;
    
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
    public @ResponseBody Collection<Empire> addEmpire(Principal pSc, Empire pData){
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
    @RolesAllowed("user")     
    @GetMapping(path="/getallempires", produces = "application/json")
    public @ResponseBody List<Empire> getAllEmpires(){
        return repositoryEmpire.findAll(Sort.by(Empire.Fields.name));
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
    public @ResponseBody Empire getEmpireByName(Principal principal, @PathVariable Long name){
        return repositoryEmpire.findByIdAndUserid(name, principal.getName());
    }

    @Operation(
        description = "Egy Birodalom védelmének meghatározása",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "404", description = "Nincs ilyen azonosítóju hős")
    })
    @PostMapping(path ="/security", produces = "application/json")
    public @ResponseBody Security setSecurityGuard(Principal pSc, Security pData){
        String uri = "";
        Empire tmp = repositoryEmpire.findByIdAndUserid(pData.getEmpire().getId(),pSc.getName());
        if(tmp!=null){
            pData.setEmpire(tmp);            
            return repositorySecurity.save(pData);
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
    public @ResponseBody List<Security> getSecurityGuard(Principal pSc, @RequestParam Long id, @RequestParam Date starttime, @RequestParam Date stoptime ){
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
    @GetMapping(path ="/attack", produces = "application/json")
    public void attack(Principal pSc, @RequestParam(name = "empireid") Long empireid, @RequestParam(name = "heroid") Long heroid){
            String uri = serviceHeroUrl+"/hero/byid/"+heroid;
            RestTemplate restTemplate = new RestTemplate();
            Hero result = restTemplate.getForObject(uri, Hero.class);
            int i=0;
    } 
     
    
    
}
