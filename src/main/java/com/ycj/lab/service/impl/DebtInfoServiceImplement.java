package com.ycj.lab.service.impl;

import com.ycj.lab.entity.DebtInfo;
import com.ycj.lab.mapper.DebtInfoMapper;
import com.ycj.lab.service.DebtInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author kk
 */
@Service
public class DebtInfoServiceImplement implements DebtInfoService {
    @Autowired
    DebtInfoMapper debtInfoMapper;

    @Override
    public List<DebtInfo> queryAllDept() {
        return debtInfoMapper.queryAllDept();
    }

    @Override
    public DebtInfo queryDeptById(long id) {
        return debtInfoMapper.queryDeptById(id);
    }

    @Override
    public List<DebtInfo> queryDebtByCreditor(int uid) {
        return debtInfoMapper.queryDebtByCreditor(uid);
    }

    @Override
    public List<DebtInfo> queryDebtByDebitor(int uid) {
        return debtInfoMapper.queryDebtByDebitor(uid);
    }

    @Override
    public List<DebtInfo> queryDebtByBill(int uid, int bid) {
        return debtInfoMapper.queryDebtByBill(uid, bid);
    }

    @Override
    public List<Integer> queryCreaditorByUid(int uid) {
        return debtInfoMapper.queryCreaditorByUid(uid);
    }

    @Override
    public List<Integer> queryDebitorByUid(int uid) {
        return debtInfoMapper.queryDebitorByUid(uid);
    }

    @Override
    public int insertDebt(DebtInfo debt) {
        return debtInfoMapper.insertDebt(debt);
    }

    @Override
    public int updateDebt(DebtInfo debt) {
        return debtInfoMapper.updateDebt(debt);
    }

    @Override
    public int deleteDebtById(Long id) {
        return debtInfoMapper.deleteDebtById(id);
    }
}
