package hu.oe.hoe.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "ability")
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@ApiModel(value = "Ability", description="Valamilyen adottság, tulajdonság számszerüsített értéke")
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;    
    
    @Column(name="name")
//    @ApiModelProperty("Képesség neve")        
    @NotNull
    @NotEmpty        
    private String name;
    
    @Column(name="description")
//    @ApiModelProperty("Képesség leírása")
    @Lob
    @NotNull
    @NotEmpty        
    private String description;
    
    @Column(name="skill")
//    @ApiModelProperty("Ügyeeség mértéke")
    @Min(0)
    @Max(100)
    private byte skill;
    
    @Column(name="brain")
//    @ApiModelProperty("Találékonyság mértéke")
    @Min(0)
    @Max(100)
    private byte brain;
    
    @Column(name="power")
//    @ApiModelProperty("Erő mértéke")
    @Min(0)
    @Max(100)
    private byte power;


@Builder    
    public Ability(Long id, String name, String description, byte skill, byte brain, byte power) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.skill = skill;
        this.brain = brain;
        this.power = power;
    }
    
}
