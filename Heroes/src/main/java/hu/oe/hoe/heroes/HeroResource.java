package hu.oe.hoe.heroes;

import hu.oe.hoe.base.DataException;
import hu.oe.hoe.model.Hero;
import hu.oe.hoe.model.Hybrid;
import hu.oe.hoe.model.Species;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@CrossOrigin("*")
@RequestMapping(path = {"/hero"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class HeroResource{
    @Autowired
    private HeroRepository repositoryHero;
    
    @Autowired
    private HybridRepository repositoryHybrid;
        
    @Value( "${service.spaces}" )
    private String serviceSpacesUrl;
    
    @Operation(
        description = "Új hős felvitele.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás felvitel")
        })
    @RolesAllowed("user")    
    @PostMapping(produces = "application/json")
    public @ResponseBody Collection<Hero> addHero(Principal pSc, @RequestBody Hero pData){
        pData.setUserid(pSc.getName());        
        repositoryHero.save(pData);
        for(Hybrid h:pData.getHybrid()){
            if(h.getPercent()!=null){
                h.setHero(pData);
                h.setSpeciesid(h.getSpecies().getId());
                repositoryHybrid.save(h);
            }
        }
        return repositoryHero.findByUseridOrderNameAsc(pData.getUserid());
    }
   
    @Operation(
        description = "Hős lista lekérdezése.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
        })
    @RolesAllowed("user")     
    @GetMapping(path="/getallheroes", produces = "application/json")
    public @ResponseBody Collection<Hero> getAllHeroes(Principal pSc){
        return repositoryHero.findAll(Sort.by(Hero.Fields.name));
    }

     @Operation(
        description = "Egy Hős lekérdezése.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"user"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
        })
    @RolesAllowed("user") 
    @GetMapping(path ="/byname/{name}", produces = "application/json")
    public @ResponseBody Hero getheroByName(Principal principal, @PathVariable Long name){
        return repositoryHero.findByIdAndUserid(name, principal.getName());
    }

    @Operation(
        description = "Egy Hős lekérdezése faji részletezéssel",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "404", description = "Nincs ilyen azonosítóju hős")
    })
    @GetMapping(path ="/byid/{id}", produces = "application/json")
    public @ResponseBody Hero getFullHeroById(@PathVariable Long id){
        try{
            String uri = "";
            Hero tmp = repositoryHero.findById(id).get();
            for(Hybrid hb : tmp.getHybrid()){
                uri = serviceSpacesUrl+"/species/byid/"+hb.getSpeciesid();
                RestTemplate restTemplate = new RestTemplate();
                Species result = restTemplate.getForObject(uri, Species.class);
                hb.setSpecies(result);
            }
           return tmp;            
        }
        catch(Exception e){throw new DataException();}
    } 
}
