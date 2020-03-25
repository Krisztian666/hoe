package hu.oe.hoe.speciesandendowments;


import hu.oe.hoe.model.Ability;
import hu.oe.hoe.model.Species;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin("*")
@RequestMapping(path = {"/species"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SpeciesResource{
    @Autowired
    private SpeciesRepository speciesRepo;
    
    @Autowired
    private AbilityRepository abilityRepo;    

    @Operation(
        description = "Új faj felvitele.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"admin"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Jogosultsági hiba.")
        })
    @RolesAllowed("admin")
    @PostMapping
    public @ResponseBody Collection<Species> addSpecies(Species pData){
        speciesRepo.save(pData);
        return speciesRepo.findOrderNameAsc();
    }

    @Operation(
        description = "Faj tulajdonságainak módosítása.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"admin"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Jogosultsági hiba.")
        })
    @RolesAllowed("admin")
    @RequestMapping(path = {"/romoveability/{sid}/{aid}"}, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Species removeAbilityFromSpecies(@PathVariable(name = "sid") long pSpeciesId, @PathVariable(name = "aid") long pAbilityId){
        Species selectedSpecies = speciesRepo.findById(pAbilityId);
        Iterator<Ability> it=selectedSpecies.getEndowments().iterator();
        Ability selectedAbility=null, tmpAbility=null;
        while(it.hasNext()){
            tmpAbility = it.next();
            if(pAbilityId == tmpAbility.getId())
                selectedAbility = tmpAbility;
        }
        if(selectedAbility!=null)
            selectedSpecies.getEndowments().remove(selectedAbility);
        speciesRepo.save(selectedSpecies);
        return selectedSpecies;
    }

    @Operation(
        description = "Faj tulajdonságainak módosítása.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"admin"})},
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "403", description = "Jogosultsági hiba.")
        })
    @RolesAllowed("admin")
    @RequestMapping(path = {"/removeability/{sid}/{aid}"}, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Species addAbilityToSpecies(@PathVariable(name = "sid") long pSpeciesId, @PathVariable(name = "aid") long pAbilityId){
        Species selectedSpecies = speciesRepo.findById(pSpeciesId);
        Ability selectedAbility = abilityRepo.findById(pAbilityId);
        selectedSpecies.getEndowments().add(selectedAbility);
        speciesRepo.save(selectedSpecies);
        return selectedSpecies;
    }

    
    @Operation(
        description = "Elérhető fajok lekérdezése.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet.")
        })    
    @RequestMapping(path = {"/getallspecies"}, method = RequestMethod.GET,  produces = "application/json")
    public @ResponseBody Collection<Species> getAllSpecies(){
        return speciesRepo.findOrderNameAsc();
    }

    @Operation(
        description = "Egy elérhető faj keresése név alapján.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet.")
    })
    @RequestMapping(path = {"/byname/{name}"}, method = RequestMethod.GET,  produces = "application/json")
    public @ResponseBody Species getSpeciesByName(@PathVariable String name){ 
        return speciesRepo.findByName(name);
    }

    @Operation(
        description = "Egy elérhető faj keresése név azonosító.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
            @ApiResponse(responseCode = "404", description = "Nincs ilyen azonosító a rendszerben.")
    })
    @RequestMapping(path = {"/byid/{id}"}, method = RequestMethod.GET,  produces = "application/json")
    public @ResponseBody Species getSpeciesById(@PathVariable Long id){ 
        Species spec = speciesRepo.findById((long)1);
        spec.getEndowments().size();
        return spec;
       //.get();
    }
   
}
