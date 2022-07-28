package com.ycj.lab.service;

import com.ycj.lab.entity.DebtInfo;

import java.util.List;

public interface DebtInfoService {

    List<DebtInfo> queryAllDept();

    DebtInfo queryDeptById(long id);

    List<DebtInfo> queryDebtByCreditor(int uid);

    List<DebtInfo> queryDebtByDebitor(int uid);

    List<DebtInfo> queryDebtByBill(int uid,int bid);

    List<Integer> queryCreaditorByUid(int uid);

    List<Integer> queryDebitorByUid(int uid);

    int insertDebt(DebtInfo debt);

    int updateDebt(DebtInfo debt);

    int deleteDebtById(Long id);
}
