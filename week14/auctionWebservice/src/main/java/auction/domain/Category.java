package auction.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="SE42_W12_1_Category")
public class Category implements Serializable{

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String description = "undefined";
    
    private Category() 
    {

    }

    public Category(String description)
    {
        this.description = description;
    }

    public String getDiscription() {
        return description;
    }
}
