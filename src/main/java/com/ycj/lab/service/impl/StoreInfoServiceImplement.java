package com.ycj.lab.service.impl;

import com.ycj.lab.entity.StoreInfo;
import com.ycj.lab.mapper.StoreInfoMapper;
import com.ycj.lab.service.StoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kk
 */
@Service
public class StoreInfoServiceImplement implements StoreInfoService {
    @Autowired
    StoreInfoMapper storeInfoMapper;

    @Override
    public StoreInfo queryStoreById(long id) {
        return storeInfoMapper.queryStoreById(id);
    }

    @Override
    public List<StoreInfo> queryStoreListByPage(int page, int size) {
        return storeInfoMapper.queryStoreListByPage(page,size);
    }

    @Override
    public List<StoreInfo> queryStoreListByTag(int page, int size, String style, String type) {
        return storeInfoMapper.queryStoreListByTag(page,size,style,type);
    }

    @Override
    public int insertStore(StoreInfo store) {
//        System.out.println(store.toString());
        String res = storeInfoMapper.queryRepetitionByName(store.getName());
//        System.out.println(res);
        if (res == null){
            return storeInfoMapper.insertStore(store);
        }else {
            return 0;
        }
    }

    @Override
    public int updateStore(StoreInfo store) {
        return storeInfoMapper.updateStore(store);
    }

    @Override
    public int updateImgById(long id, String imgType, String img) {
        String res = storeInfoMapper.queryExistenceById(id,imgType);
//        System.out.println("StoreService "+res);
        if (res == null || res.isEmpty()) {
//            System.out.println("StoreService emp");
            return storeInfoMapper.updateImgById(id,imgType,img);
        }else {
//            System.out.println("StoreService nemp");
            img = res + "&" + img;
            return storeInfoMapper.updateImgById(id,imgType,img);
        }
    }

    @Override
    public int updateStoreStateById(long id, int state) {
        return storeInfoMapper.updateStoreStateById(id,state);
    }
}
