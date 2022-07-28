package com.ycj.lab.mapper;

import com.ycj.lab.entity.StoreInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreInfoMapper {

    //根据sid 查询 store
    StoreInfo queryStoreById(@Param("id") long id);
    //分页查询 store
    List<StoreInfo> queryStoreListByPage(@Param("page") int page,@Param("size") int size);
    //分页查询 + 按标签查询
    List<StoreInfo> queryStoreListByTag(@Param("page") int page,
                                        @Param("size") int size,
                                        @Param("style") String style,
                                        @Param("type") String type);
    //judge repetition
    String queryRepetitionByName(@Param("name") String name);
    //judge cover or background or picture existence
    String queryExistenceById(@Param("id") long id,@Param("value") String value);
    //insert store
    int insertStore(StoreInfo store);
    //update storeInfo
    int updateStore(StoreInfo store);
    //update storeImage
    int updateImgById(@Param("id") long id,@Param("imgType") String imgType,@Param("img") String img);
    //update storeState
    int updateStoreStateById(@Param("id") long id,@Param("state") int state);

}
