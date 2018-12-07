package model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Data;
import model.user.User;

@Entity
@Table(name = "trade")
@Data
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_ccy")
    private String name;
    @Column(name = "type_trade")
    private String type;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "create_date")
    private LocalDateTime localDateTime;
    @Column(name = "active")
    private boolean active;
    @Version
    private Integer version;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User user;


}
