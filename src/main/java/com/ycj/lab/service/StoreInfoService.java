package com.ycj.lab.service;

import com.ycj.lab.entity.StoreInfo;

import java.util.List;

public interface StoreInfoService {
    StoreInfo queryStoreById(long id);

    List<StoreInfo> queryStoreListByPage(int page, int size);

    List<StoreInfo> queryStoreListByTag(int page, int size, String style, String type);

    int insertStore(StoreInfo store);

    int updateStore(StoreInfo store);

    int updateImgById(long id, String imgType, String img);

    int updateStoreStateById(long id, int state);
}
