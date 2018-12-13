package model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "nbu_course")
@Data
public class Nbu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long course_id;
    @Column(name = "article")
    private String article;
    @Column(name = "text")
    private String currencyName;
    @Column(name = "course")
    private Double course;
    @Column(name = "code")
    private String code;
    @Column(name = "exchangeDate")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate exchangeDate;


}
