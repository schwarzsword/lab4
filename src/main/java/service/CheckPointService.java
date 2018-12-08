package service;

import entity.PointEntity;

import java.util.List;

public interface CheckPointService {
    String savePoint(String strX, String strY, String strR);

    void logOut();

    List<PointEntity> getPoints();

}
