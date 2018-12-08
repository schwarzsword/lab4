import config.database.DatabaseConfig;
import entity.UserEntity;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import repository.UserRepository;

public class Main {
    public static void main(String args[]) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        UserEntity me = new UserEntity();
        me.setLogin("SchwarzSword");
        me.setPassword(BCrypt.hashpw("letmepass", BCrypt.gensalt()));
        userRepository.save(me);
        UserEntity Igor = new UserEntity();
        Igor.setLogin("Radiant");
        Igor.setPassword(BCrypt.hashpw("Magnusnotatraitor", BCrypt.gensalt()));
        userRepository.save(Igor);
        UserEntity Yarkeev = new UserEntity();
        Yarkeev.setLogin("Bleizard");
        Yarkeev.setPassword(BCrypt.hashpw("toootoorooo", BCrypt.gensalt()));
        userRepository.save(Yarkeev);
        UserEntity Uskov = new UserEntity();
        Uskov.setLogin("IvanUskov");
        Uskov.setPassword(BCrypt.hashpw("proshayte", BCrypt.gensalt()));
        userRepository.save(Uskov);
        UserEntity admin = new UserEntity();
        admin.setLogin("admin");
        admin.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        userRepository.save(admin);
    }
}
