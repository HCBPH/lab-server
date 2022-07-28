package com.ycj.lab.controller;

import com.ycj.lab.dto.PartyDetailInfo;
import com.ycj.lab.entity.PartyInfo;
import com.ycj.lab.result.Result;
import com.ycj.lab.service.PartyBillService;
import com.ycj.lab.service.PartyInfoService;
import com.ycj.lab.service.PartyMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/party")
public class PartyInfoController {

    @Autowired
    PartyInfoService partyInfoService;
    @Autowired
    PartyBillService partyBillService;
    @Autowired
    PartyMemberService partyMemberService;

    @RequestMapping(value = "/find_one_party", method = RequestMethod.POST)
    public Result findOneParty(int pId) {
        try {
            PartyInfo partyInfo = partyInfoService.findOneGroup(pId);
            int countMember = partyMemberService.countOnePartyMember(pId);
            double money = partyBillService.sumOnePartyBillMoney(pId);
            PartyDetailInfo partyDetailInfo = new PartyDetailInfo(partyInfo.getPId(), partyInfo.getGId(), partyInfo.getCreatorId(), partyInfo.getTitle(),
                    partyInfo.getPlace(), partyInfo.getRId(), partyInfo.getLocation(), countMember, money, partyInfo.getCreateTime(),
                    partyInfo.getStage());
            log.info(String.valueOf(partyDetailInfo));
            return Result.success(partyDetailInfo);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            return Result.error();
        }
    }

    @RequestMapping(value = "/edit_party", method = RequestMethod.POST)
    public Result editParty(@RequestParam(value = "pId", required = false, defaultValue = "") int pId,
                            @RequestParam(value = "title", required = false, defaultValue = "") String title,
                            @RequestParam(value = "place", required = false, defaultValue = "") String place,
                            @RequestParam(value = "rId", required = false, defaultValue = "") String rId,
                            @RequestParam(value = "location", required = false, defaultValue = "") String location) {
//        logger.info(String.valueOf(title.isEmpty()));
//        logger.info(title);
        if (!title.isEmpty()) {
            partyInfoService.editParty(pId, "title", title);
        }
        if (!place.isEmpty()) {
            partyInfoService.editParty(pId, "place", place);
        }
        if (!rId.isEmpty()) {
            partyInfoService.editParty(pId, "rid", rId);
        }
        if (!location.isEmpty()) {
            partyInfoService.editParty(pId, "location", location);
        }
        return Result.success("Modified successfully");
    }

    @RequestMapping(value = "/find_party_by_group", method = RequestMethod.POST)
    public Result findPartyByGroup(@RequestParam Long gId) {

        try {
            List<PartyInfo> partyInfoList = partyInfoService.findPartyByGroupId(gId);
            //返回值列表
            List<PartyDetailInfo> partyDetailInfoList = new ArrayList<>();
            for (PartyInfo partyInfo : partyInfoList) {
                int countMember = partyMemberService.countOnePartyMember(partyInfo.getPId());
                double money = partyBillService.sumOnePartyBillMoney(partyInfo.getPId());
                PartyDetailInfo partyDetailInfo = new PartyDetailInfo(partyInfo.getPId(), partyInfo.getGId(), partyInfo.getCreatorId(), partyInfo.getTitle(),
                        partyInfo.getPlace(), partyInfo.getRId(), partyInfo.getLocation(), countMember, money, partyInfo.getCreateTime(),
                        partyInfo.getStage());
                partyDetailInfoList.add(partyDetailInfo);
            }
            return Result.success(partyDetailInfoList);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            return Result.error();
        }
    }

    @RequestMapping(value = "/create_party_by_group", method = RequestMethod.POST)
    public Result createPartyByGroup(@RequestParam Long gId,
                                     @RequestParam int creatorId,
                                     @RequestParam String title,
                                     @RequestParam(value = "place", required = false, defaultValue = "null") String place,
                                     @RequestParam(value = "rId", required = false, defaultValue = "0") String rId,
                                     @RequestParam(value = "location", required = false, defaultValue = "null") String location,
                                     @RequestParam String date,
                                     @RequestParam int type) {
        try {
            int changRId = Integer.parseInt(rId);
            PartyInfo partyInfo = new PartyInfo();
            partyInfo.setGId(gId);
            partyInfo.setCreatorId(creatorId);
            partyInfo.setTitle(title);
            partyInfo.setPlace(place);
            partyInfo.setRId(Integer.parseInt(rId));
            partyInfo.setLocation(location);
            partyInfo.setCreateTime(date);
            partyInfo.setStage(type);

            partyInfoService.createPartyByGroup(partyInfo);
            partyMemberService.joinToPartyCreator(creatorId, partyInfo.getPId());
            return Result.success(partyInfo.getPId());


        } catch (Exception e) {
            log.error(String.valueOf(e));
            return Result.error();
        }
    }

    @RequestMapping(value = "/bind_party_to_group", method = RequestMethod.POST)
    public Result bindPartyToGroup(@RequestParam int pId, @RequestParam Long gId) {
        try {
            int resultCode = partyInfoService.bindPartyToGroup(pId, gId);
            if (resultCode == 1) {
                return Result.success("Bind successfully");
            } else {
                return Result.error("Bind failed");
            }
        } catch (Exception e) {
            return Result.error();
        }
    }

    @RequestMapping(value = "/release_party", method = RequestMethod.POST)
    public Result releaseParty(@RequestParam int pId, @RequestParam int creatorId) {
        try {
            int resultCode = partyInfoService.releaseParty(pId, creatorId);
            if (resultCode == 1) {
                return Result.success("Release successfully");
            } else {
                return Result.error("CreatorId is false");
            }

        } catch (Exception e) {
            return Result.error();
        }
    }

    @RequestMapping(value = "/change_party_stage", method = RequestMethod.POST)
    public Result changePartyStage(@RequestParam int pId,
                                   @RequestParam int creatorId,
                                   @RequestParam int stage) {
        try {
            int resultCode = partyInfoService.changePartyStage(pId, creatorId, stage);
            if (resultCode == 1) {
                return Result.success("Change stage successfully");
            } else {
                return Result.error("CreatorId is false");
            }
        } catch (Exception e) {
            return Result.error();
        }
    }
}
