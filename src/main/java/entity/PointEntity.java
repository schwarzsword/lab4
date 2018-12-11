package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "point", schema = "public", catalog = "s243884")
@Entity
@Data
public class PointEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "x", nullable = false, precision = 0)
    private double x;
    @Basic
    @Column(name = "y", nullable = false, precision = 0)
    private double y;
    @Basic
    @Column(name = "r", nullable = false, precision = 0)
    private int r;
    @Basic
    @Column(name = "entering", nullable = false, length = 20)
    private String entering;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent", referencedColumnName = "username")
    private UserEntity labuserByParent;

    protected PointEntity() {
    }

    public PointEntity(double x, double y, int r, UserEntity parent) {
        this.x = x;
        this.y = y;
        this.r = r;
        isInArea(x, y, r);
        this.labuserByParent = parent;
    }



    private void isInArea(double x, double y, double r) {
        if (x < -3 || x > 5 || y < -4 || y > 4 || r < 1 || r > 5) {
            this.setEntering("Invalid operands");
        } else {
            if (
                    x >= 0 && y >= 0 && x <= r && y <= (r / 2) ||
                            x >= 0 && y <= 0 && y >= (x - (r / 2))||
                            y >= 0 && x <= 0 && (x*x + y*y) <= r*r
            ) {
                this.setEntering("true");
            } else this.setEntering("false");
        }
    }

}
