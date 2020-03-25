package hu.oe.hoe.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "species")
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@ApiModel(description="Faj és a hozzá tartozó speciális képességek.")
public class Species {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;    
    
    @Column(name="name")
//    @ApiModelProperty("Neve/kódja")        
    private String name;
    
    @Column(name="description")
//    @ApiModelProperty("Leírás")
    @Lob
    private String description;
   
    @ManyToMany(fetch = FetchType.LAZY)
//    @ApiModelProperty("Faji képesség lista")
    private List<Ability>  endowments = new ArrayList<>();

    @Builder
    public Species(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    
}
