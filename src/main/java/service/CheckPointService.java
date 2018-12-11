package service;

import entity.PointEntity;
import entity.UserEntity;

import java.util.List;

public interface CheckPointService {
    String savePoint(String strX, String strY, String strR, UserEntity parent);

    List<PointEntity> getPoints(UserEntity userEntity);

}
