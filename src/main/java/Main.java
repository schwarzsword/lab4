import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import config.database.DatabaseConfig;
import entity.PointEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.PointRepository;
import repository.UserRepository;
import service.AuthorizationService;
import service.CheckPointService;

import java.util.List;

public class Main {

    public static void main(String args[]) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        AuthorizationService authorizationService = ctx.getBean(AuthorizationService.class);
        CheckPointService checkPointService = ctx.getBean(CheckPointService.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());

        UserEntity user = authorizationService.loadUserByUsername("schwarzsword").get();
        List<PointEntity> list = checkPointService.getPoints(user);

        String str = null;
        try {
            str = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
