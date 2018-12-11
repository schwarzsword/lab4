import config.database.DatabaseConfig;
import entity.UserEntity;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import repository.UserRepository;

public class Main {
    public static void main(String args[]) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        UserRepository bean = ctx.getBean(UserRepository.class);
        UserEntity u1 = new UserEntity();
        u1.setUsername("bleizard");
        u1.setPassword(BCrypt.hashpw("toootoorooo", BCrypt.gensalt()));
        bean.save(u1);
        UserEntity u2 = new UserEntity();
        u2.setUsername("ivanuskov");
        u2.setPassword(BCrypt.hashpw("proschayte", BCrypt.gensalt()));
        bean.save(u2);
        UserEntity u3 = new UserEntity();
        u3.setUsername("schwarzsword");
        u3.setPassword(BCrypt.hashpw("qwe", BCrypt.gensalt()));
        bean.save(u3);
        UserEntity u4 = new UserEntity();
        u4.setUsername("radiant");
        u4.setPassword(BCrypt.hashpw("magnus", BCrypt.gensalt()));
        bean.save(u4);
        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        bean.save(user);
    }
}
