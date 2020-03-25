package hu.oe.hoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    
    @Column(name="starttime")
    private Timestamp starttime;
    
    @Column(name="stoptime")
    private Timestamp stoptime;
    
    @Column(name="active")
    @Nullable
    private Boolean active;
    
    @Column(name="price")
    private Long price;
    
    @Column(name="heroid")
    private Long heroid;
    
    @Transient
    private Long empireid;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Empire empire;
}
