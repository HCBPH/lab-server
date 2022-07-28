package com.ycj.lab.controller;

import com.ycj.lab.dto.UserDetailDto;
import com.ycj.lab.entity.PartyMembers;
import com.ycj.lab.entity.User;
import com.ycj.lab.result.Result;
import com.ycj.lab.service.PartyMemberService;
import com.ycj.lab.service.UserService;
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
public class PartyMemberController {
    @Autowired
    PartyMemberService partyMemberService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/find_one_party_member", method = RequestMethod.POST)
    public Result findOnePartyMember(@RequestParam int pId) {
//        List<Integer> list = partyMemberService.findOnePartyMember(pId);
//        PartyInfo partyInfo = partyInfoService.findOneGroup(pId);
//        int resultCreatorId = partyInfo.getPId();
        List<PartyMembers> list = partyMemberService.findOnePartyMemberDetail(pId);
//        //增加创建者id
//        PartyMembers partyMembers = new PartyMembers();
//        partyMembers.setId(0);
//        partyMembers.setPId(pId);
//        partyMembers.setUId(resultCreatorId);
//        partyMembers.setType(1);
//        list.add(partyMembers);
        List<UserDetailDto> userDetailDtoList = new ArrayList<>();
        for(PartyMembers partyMember : list){
            User user = userService.getUserDetail(partyMember.getUId());
//            logger.info(String.valueOf(user));
            UserDetailDto userDetailDto = new UserDetailDto(partyMember.getUId(),partyMember.getType(),
                    user.getUsername(),user.getName(),user.getProfile());
            userDetailDtoList.add(userDetailDto);
        }
//        logger.info(String.valueOf(userDetailDtoList));
        return Result.success(userDetailDtoList);
    }

    @RequestMapping(value = "/join_to_party", method = RequestMethod.POST)
    public Result joinToParty(@RequestParam int uId,
                              @RequestParam int pId) {
        int resultCode = 0;
        try {
            Integer isDup = partyMemberService.findPartyMemberDup(uId,pId);
            if(isDup != null){
                resultCode = partyMemberService.insertPartyMemberDup(uId,pId);
//                log.info(String.valueOf(resultCode));
            }else {
                resultCode = partyMemberService.joinToParty(uId, pId);
            }
            if (resultCode == 1){
                return Result.success("Join to party successfully");
            }else {
                return Result.error();
            }
        }catch (Exception e){
            return Result.error();
        }
    }

    @RequestMapping(value = "/leave_party", method = RequestMethod.POST)
    public Result leaveParty(@RequestParam int uId,@RequestParam int pId){
        try {
            partyMemberService.leaveParty(uId, pId);
            return Result.success("Leave party successfully");
        }catch (Exception e){
            return Result.error();
        }
    }

    @RequestMapping(value = "/update_user_type", method = RequestMethod.POST)
    public Result updateUserType(@RequestParam int uId,
                                 @RequestParam int pId,
                                 @RequestParam int type){
        try {
            int resultCode = partyMemberService.updateUserType(uId, pId, type);;
            log.error(String.valueOf(resultCode));
            if (resultCode==1){
                return Result.success("Update user type successfully");
            }else {
                return Result.error("Update failed");
            }
        }catch (Exception e){
            log.error(String.valueOf(e));
            return Result.error();
        }
    }
}
