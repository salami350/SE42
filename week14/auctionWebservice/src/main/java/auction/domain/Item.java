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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import nl.fontys.util.Money;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@Table (name = "SE42_W12_1_Item")
@NamedQueries({
    @NamedQuery(name = "Item.findByDescription", query = "select a from Item as a where a.description = :description"),
    @NamedQuery(name = "Item.findWithUserAndCatAndDesc", query = "select a from Item as a where a.seller = :seller AND a.category = :category AND a.description = :description")
})
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Item implements Comparable, Serializable{

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ManyToOne (cascade = { /*CascadeType.PERSIST,*/CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH } )
    /*@CascadeOnDelete*/
    private User seller;
    @ManyToOne /*(cascade = CascadeType.PERSIST)*/
    private Category category;
    private String description;
    @OneToOne (mappedBy = "item", cascade = CascadeType.PERSIST) 
    private Bid highest;

    public Item()
    {
        
    }
    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
        seller.addItem(this);
    }
    @WebMethod
    public Long getId() {
        return id;
    }
    @WebMethod
    public User getSeller() {
        return seller;
    }
    @WebMethod
    public Category getCategory() {
        return category;
    }
    @WebMethod
    public String getDescription() {
        return description;
    }
    @WebMethod
    public Bid getHighestBid() {
        return highest;
    }
    @WebMethod
    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount, this);
        return highest;
    }
    
    @Override
    @WebMethod
    public int compareTo(Object arg0) {
        //TODO
        return (int) (highest.getAmount().getCents() - ((Item) arg0).getHighestBid().getAmount().getCents());
    }
    
    @Override
    @WebMethod
    public boolean equals(Object o) {

        if(o == null)
        {
            return false;
        }
        Item a = (Item) o;
         
        if(a.getSeller() == null || a.getCategory() == null || a.getDescription() == null || a.getHighestBid() == null)
        {
            return false;
        }
        else if(this.seller != a.getSeller())
        {
            return false;
        }
        else if(this.category != a.getCategory())
        {
            return false;
        }
        else if(!description.equals(a.getDescription()))
        {
            return false;
        }
        else if(highest != a.getHighestBid())
        {
            return false;
        }
        else
        {
            return true;
        }  
    }

    @Override
    @WebMethod
    public int hashCode()
    {
        long hash = 5;
        
        if(this.id != null)
        {
            hash += this.id.hashCode();
        }
        
        if(this.seller != null)
        {
            hash += this.seller.hashCode();
        }
        
        if(this.category != null)
        {
            hash += this.category.hashCode();
        }
        
        if(this.description != null)
        {
            hash += this.description.hashCode();
        }
        
        if(this.highest != null)
        {
            hash += this.highest.hashCode();
        }
        
        return (int) hash;
    }
}
