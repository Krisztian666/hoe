package hu.oe.hoe.speciesandendowments;

import hu.oe.hoe.base.DataException;
import hu.oe.hoe.model.Ability;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
//@ApiModel(description="Képességekhez kapcsolódó számszerüsített tulajdonságértékek kezelése.")
@RequestMapping(value = "/ability")
public class AbilityResource {
    @Autowired
    private AbilityRepository abilityRepo;

    @Operation(
        description = "Egy meglévő képesség módosítása, ha még nem létezik akkor létrehozása.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"admin"})},    
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés.")
    })   
    @RequestMapping(produces = "application/json", method = RequestMethod.PUT)
    @RolesAllowed("admin")
    public @ResponseBody Collection<Ability> modifyEndowments(@RequestBody Ability pData){
        if(pData.getId()>0){
            Optional<Ability> ability = abilityRepo.findById(pData.getId());
            if(ability!=null){
                BeanUtils.copyProperties(pData, ability.get(), Ability.Fields.id);
                abilityRepo.save(ability.get());
            }
            else{
                abilityRepo.save(pData);
            }
        }
        else {
                abilityRepo.save(pData);
        }
        return abilityRepo.findOrderNameAsc();
    }
 
    @Operation(
        description = "Egy új képesség felvitele, ha még nem létezik.",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"admin"})},    
        responses = {
            @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés."),
            @ApiResponse(responseCode = "400", description = "Ezen a néven már van egy másik tulajdonság")
    })   
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @RolesAllowed("admin")    
    public @ResponseBody Collection<Ability> addEndowments(@RequestBody Ability pData){
        if(abilityRepo.findByName(pData.getName())==null){
            abilityRepo.save(pData);
            return abilityRepo.findOrderNameAsc();
        }
        throw new DataException();
    }

    
    @Operation(
        description = "Az összes képesség lekérdezése",
        responses = {
        @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés")
    })       
    @RequestMapping(path="/getabilities", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody Collection<Ability> getAllEndowments(){
        return abilityRepo.findOrderNameAsc();
    }

  
    @Operation(
        description = "Képesség lekérdezése lekérdezése név alapján",
        responses = {
        @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés")
    })       
    @RequestMapping(path ="/byname/{name}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody Ability getEndowmentsByName(@PathVariable String name){
        return abilityRepo.findByName(name);
    }
   
    @Operation(
        description = "Képesség törlése id alapján",
        security ={@SecurityRequirement(name = "jwt-token", scopes = {"admin"})},    
        responses = {
        @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés")
    })       
    @RequestMapping(path ="/delete/{id}", produces = "application/json", method = RequestMethod.DELETE)
    @RolesAllowed("admin")  
    public void deleteEndowments(@PathVariable long id){
        if(abilityRepo.existsById(id))
            this.abilityRepo.deleteById(id);
        else throw new DataException();
    }
}
