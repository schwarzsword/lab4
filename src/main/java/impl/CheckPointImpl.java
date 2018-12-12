package impl;

import entity.PointEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PointRepository;
import service.CheckPointService;

import java.util.List;

@Service("checkPointService")
public class CheckPointImpl implements CheckPointService {

    final
    PointRepository pointRepository;

    @Autowired
    public CheckPointImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }


    @Override
    public List<PointEntity> getPoints(UserEntity userEntity) {
        return pointRepository.findAllByLabuserByParent(userEntity);
    }

    @Transactional
    @Override
    public String savePoint(String strX, String strY, String strR, UserEntity parent) {
        double x = Double.parseDouble(strX);
        double y = Double.parseDouble(strY);
        int r = Integer.parseInt(strR);
        PointEntity point = new PointEntity(x, y, r, parent);
        pointRepository.save(point);
        return point.getEntering();
    }


}
