package project.shopping.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.shopping.domain.Category;
import project.shopping.exception.NotEnoughStockException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStock(int qauntity) {
        this.stockQuantity += qauntity;
    }

    public void removeStock(int qauntity) {
        int restStock = this.stockQuantity -= qauntity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
