package com.ycj.lab.controller;

import com.ycj.lab.entity.DebtInfo;
import com.ycj.lab.result.Result;
import com.ycj.lab.service.DebtInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/debt")
public class DebtInfoController {
    @Autowired
    DebtInfoService debtInfoService;

    @RequestMapping(value = "/find_ debt_all", method = RequestMethod.POST)
    public Result findDebtAll() {
        try {
            List<DebtInfo> debtInfoList = debtInfoService.queryAllDept();
            return Result.success(debtInfoList);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_one_debt", method = RequestMethod.POST)
    public Result findOneDebt(@RequestParam long id) {
        try {
            DebtInfo debtInfo = debtInfoService.queryDeptById(id);
            return Result.success(debtInfo);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_debt_by_creditor", method = RequestMethod.POST)
    public Result findDebtByCreditor(@RequestParam int uid) {
        try {
            List<DebtInfo> debtInfoList = debtInfoService.queryDebtByCreditor(uid);
            return Result.success(debtInfoList);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_debt_by_debitor", method = RequestMethod.POST)
    public Result findDebtByDebitor(@RequestParam int uid) {
        try {
            List<DebtInfo> debtInfoList = debtInfoService.queryDebtByDebitor(uid);
            return Result.success(debtInfoList);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_debt_by_bill", method = RequestMethod.POST)
    public Result findDebtByBill(@RequestParam int uid, @RequestParam int bid) {
        try {
            List<DebtInfo> debtInfoList = debtInfoService.queryDebtByBill(uid, bid);
            return Result.success(debtInfoList);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_debitor_by_user", method = RequestMethod.POST)
    public Result findDebitorByUser(@RequestParam int uid) {
        try {
            List<Integer> list = debtInfoService.queryDebitorByUid(uid);
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/find_creditor_by_user", method = RequestMethod.POST)
    public Result findCreditorByUser(@RequestParam int uid) {
        try {
            List<Integer> list = debtInfoService.queryCreaditorByUid(uid);
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/create_debt", method = RequestMethod.POST)
    public Result createDebt(@RequestBody DebtInfo debtInfo) {
        try {
            int res = debtInfoService.insertDebt(debtInfo);
            log.info(String.valueOf(res));
            return Result.success();
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/edit_debt", method = RequestMethod.POST)
    public Result editDebt(@RequestBody DebtInfo debtInfo) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        debtInfo.setUpdateTime(dateFormat.format(date));
        try {
            int res = debtInfoService.updateDebt(debtInfo);
            if (res == 1) {
                return Result.success("update successful");
            } else {
                return Result.error("update failed");
            }
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    @RequestMapping(value = "/delete_debt", method = RequestMethod.POST)
    public Result deleteDebt(@RequestParam long id) {
        try {
            int res = debtInfoService.deleteDebtById(id);
            if (res == 1) {
                return Result.success("delete successful");
            } else {
                return Result.error("delete failed");
            }
        } catch (Exception e) {
            return Result.error(e);
        }
    }
}
