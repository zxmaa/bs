package com.bs.mall.controller.fore;

import com.bs.mall.controller.BaseController;
import com.bs.mall.dao.pojo.Address;
import com.bs.mall.dao.pojo.UserAddress;
import com.bs.mall.dto.ForeUserDto;
import com.bs.mall.dto.res.ForeUserAddressResDto;
import com.bs.mall.service.fore.IAddressService;
import com.bs.mall.service.fore.IUserAddressService;
import com.bs.mall.service.fore.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ForeUserAddressController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private IUserAddressService userAddressService;
    /**
     * 转到用户地址管理页
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/userAddress")
    public String goToUserAddressPage(HttpSession session, Model model){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        String addressId = "110000";
        String cityAddressId = "110100";
        logger.info("获取省份信息");
        List<Address> addressList = addressService.getAllProvince();
        logger.info("获取以addressId为省的市级地址信息");
        List<Address> cityAddress = addressService.getListByAreaId(addressId);
        logger.info("获取cityAddressId为市的区级地址信息");
        List<Address> districAddress = addressService.getListByAreaId(cityAddressId);
        //查询出用户所有的常用地址
        List<ForeUserAddressResDto> userAllAddress = userAddressService.getAllUserAddress(userId);

        model.addAttribute("addressId",addressId);
        model.addAttribute("cityAddressId",cityAddressId);
        model.addAttribute("addressList",addressList);
        model.addAttribute("cityAddress",cityAddress);
        model.addAttribute("districAddress",districAddress);
        model.addAttribute("userAllAddress",userAllAddress);

        logger.info("转到前台，用户地址管理页");
        return "user/userAddresPage";

    }

    /**
     * 添加用户地址
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/addUserAddress")
    public String addUserAddress(HttpSession session,Model model,UserAddress userAddress){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }
        ForeUserDto user = userService.findUserByUsereName((String) o);
        Integer userId = user.getUserId();

        userAddress.setUserId(userId);
        userAddressService.addUserAddress(userAddress);
        logger.info("重新加载地址管理页面");
        return "/userAddress";
    }

    /**
     * 删除用户弟子
     * @param session
     * @param userAddressId
     * @return
     */
    @RequestMapping("/delUserAddress")
    public String delUserAddress(HttpSession session, @RequestParam  Integer userAddressId){
        logger.info("检查用户是否登录");
        Object o = checkUser(session);
        if(null == o){
            return "redirect:/login";
        }

        userAddressService.deleteUserAddress(userAddressId);
        logger.info("重新加载地址管理页面");
        return "/userAddress";
    }

}
