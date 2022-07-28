package com.ycj.lab.controller;

import com.ycj.lab.entity.PartyBill;
import com.ycj.lab.result.Result;
import com.ycj.lab.service.PartyBillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bill")
public class PartyBillController {
    @Autowired
    PartyBillService partyBillService;

    @RequestMapping(value = "/set_bill_event", method = RequestMethod.POST)
    public Result setBillEvent(@RequestParam int pId,
                               @RequestParam String event,
                               @RequestParam double money,
                               @RequestParam String remark,
                               @RequestParam int creatorId) {
        try {
            partyBillService.setBillEvent(pId, event, money, remark, creatorId);
            return Result.success("Set bill event successfully");
        } catch (Exception e) {
            return Result.error();
        }
    }

    @RequestMapping(value = "/show_bill", method = RequestMethod.POST)
    public Result showBill(@RequestParam int pId) {
        List<PartyBill> list = partyBillService.showBill(pId);
        return Result.success(list);
    }
}
