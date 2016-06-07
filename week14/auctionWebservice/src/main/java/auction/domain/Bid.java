package auction.domain;

import java.io.Serializable;
import javax.jws.WebMethod;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
@Table (name="SE42_W12_1_Bid")
@XmlRootElement
public class Bid implements Serializable{

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @XmlAttribute
    private Long id;
    
    @XmlAttribute
    private FontysTime time;
    @ManyToOne
    @XmlAttribute
    private User buyer;
   
    @XmlAttribute
    private Money amount;
    //persisten en test schrijven
    @OneToOne @JoinColumn (nullable = false) 
    @XmlAttribute
    private Item item;
    
    public Bid()
    {
        
    }
    
    public Bid(User buyer, Money amount, Item item) {
        this.buyer = buyer;
        this.amount = amount;
        this.item = item;
    }

    @WebMethod
    public FontysTime getTime() {
        return time;
    }

    @WebMethod
    public User getBuyer() {
        return buyer;
    }

    @WebMethod
    public Money getAmount() {
        return amount;
    }
}
