package com.ycj.lab.mapper;

import com.ycj.lab.entity.DebtInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DebtInfoMapper {
    //查询所有债务信息
    List<DebtInfo> queryAllDept();
    //查询单条债务信息->id
    DebtInfo queryDeptById(@Param("id") long id);
    //按照债权人查询其债务
    List<DebtInfo> queryDebtByCreditor(@Param("uid") int uid);
    //按照债务人查询其债务
    List<DebtInfo> queryDebtByDebitor(@Param("uid") int uid);
    //查询旗下有无该用户相关债务->bid
    List<DebtInfo> queryDebtByBill(@Param("uid") int uid,@Param("bid") int bid);
    //查询其债权人->uid
    List<Integer> queryCreaditorByUid(@Param("uid") int uid);
    //查询其债务人->uid
    List<Integer> queryDebitorByUid(@Param("uid") int uid);
    //创建债务信息
    int insertDebt(DebtInfo debt);
    //修改债务信息
    int updateDebt(DebtInfo debt);
    //删除债务信息
    int deleteDebtById(@Param("id") Long id);

}
