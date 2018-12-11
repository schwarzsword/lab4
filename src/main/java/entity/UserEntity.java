package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Table(name = "labuser", schema = "public", catalog = "s243884")
@Entity
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @OneToMany(mappedBy = "labuserByParent", fetch = FetchType.LAZY)
    private Collection<PointEntity> pointsByUsername;



    public Collection<PointEntity> getPointsByUsername() {
        return pointsByUsername;
    }

    public void setPointsByUsername(Collection<PointEntity> pointsByLogin) {
        this.pointsByUsername = pointsByLogin;
    }
}
