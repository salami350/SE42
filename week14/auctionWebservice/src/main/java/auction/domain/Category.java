package auction.domain;

import java.io.Serializable;
import javax.jws.WebMethod;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table (name="SE42_W12_1_Category")
@XmlRootElement
public class Category implements Serializable{

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @XmlAttribute
    private Long id;
    @Column
    @XmlAttribute
    private String description = "undefined";
    
    private Category() 
    {

    }

    public Category(String description)
    {
        this.description = description;
    }

    @WebMethod
    public String getDiscription() {
        return description;
    }
}
