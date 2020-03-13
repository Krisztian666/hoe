package hu.oe.hoe.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "securityguard")
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SecurityGuard {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;    
        
    @ManyToOne
    private Empire empire;
    
    @Column(name="starttime")
    private Date starttime;
    
    @Column(name="stoptime")
    private Date stoptime;
    
    @Column(name="active")
    @Nullable
    private Boolean active;
    
    @Column(name="price")
    private Long price;
    
}
