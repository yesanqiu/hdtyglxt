package com.example.hdtyglxt.controller;


import com.example.hdtyglxt.base.dto.ResultDTO;

import com.example.hdtyglxt.base.util.ResultUtil;
import com.example.hdtyglxt.dto.BlackDTO;
import com.example.hdtyglxt.dto.GroundBookDTO;
import com.example.hdtyglxt.entity.*;
import com.example.hdtyglxt.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private GroundService groundService;
    @Autowired
    private GroundBookService groundBookService;
    @Autowired
    private GbTimeService gbTimeService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private RepudiationService repudiationService;
    @Autowired
    private UserService userService;
    @RequestMapping("/ground.html")
    public String toground(){
        return "ground";
    }
    @RequestMapping("/toaddground")
    public String toaddground(){
        return "add_ground";
    }
    @GetMapping("/toupdateground{gid}")
    public String toupdateground( @PathVariable("gid") Integer gid, Model model)throws Exception{
        Ground ground = groundService.get(gid);
        model.addAttribute("ground",ground);
        return "update_ground";
    }

    /**
     * 跳到场地预约页面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/tobookground")
    public String tobookground(Model model) throws Exception{
        Ground ground = new Ground();
        ground.setGstatus("0");
        List<Ground> groundList = groundService.findByParams(ground);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> datalist = new ArrayList<>();
        List<Integer> weeklist = new ArrayList<Integer>();
        List<String> currentWeek = new ArrayList<>();
        String msg = null;
        int num = 7;
        for (int i=0;i<=num;i++){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, + i);
            Date day = c.getTime();
            int CurrentWeek = c.get(Calendar.DAY_OF_WEEK);
            weeklist.add(CurrentWeek);
            String preday = sdf.format(day);
            datalist.add(preday);
        }
        for (int j = 0 ; j<weeklist.size();j++){
            switch (weeklist.get(j)){
                case 1:
                    msg = "星期日";
                    currentWeek.add(msg);
                    break;
                case 2:
                    msg = "星期一";
                    currentWeek.add(msg);
                    break;
                case 3:
                    msg = "星期二";
                    currentWeek.add(msg);
                    break;
                case 4:
                    msg = "星期三";
                    currentWeek.add(msg);
                    break;
                case 5:
                    msg = "星期四";
                    currentWeek.add(msg);
                    break;
                case 6:
                    msg = "星期五";
                    currentWeek.add(msg);
                    break;
                case 7:
                    msg = "星期六";
                    currentWeek.add(msg);
                    break;
            }
        }

        model.addAttribute("datalist",datalist);
        model.addAttribute("currentWeek",currentWeek);
        model.addAttribute("groundList",groundList);
        return "book_ground";
    }
    /**
    添加场地
     **/
    @PostMapping("/addground")
    public String saveground(Ground ground)throws Exception{
        ground.setGstatus("0");
        groundService.save(ground);
        return "redirect:/ground.html";
    }

    /**
     * 修改场地
     * @param ground
     * @return
     * @throws Exception
     */
    @PostMapping("/updateground")
    public String updateGround(Ground ground)throws Exception{
        
        groundService.update(ground);
        return "redirect:/ground.html";
    }

    /**
     * 跳的预约时间段列表
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/tobookdetailed")
    public String toBookDetailed(HttpServletRequest request,Model model)throws Exception{
        String queryString = request.getQueryString();
        String[] split = queryString.split("&");
        String[] splitgid = split[0].split("=");
        int gid = Integer.parseInt(splitgid[1]);
        GbTime gbTime = new GbTime();
        gbTime.setGbGid(gid);
        List<GbTime> gbTimeList = gbTimeService.findByParams(gbTime);
        String[] splitdate = split[1].split("=");
        String[] splitcost = split[2].split("=");
        String cost = splitcost[1];
        String currtdate =  splitdate[1];
        model.addAttribute("gbTimeList",gbTimeList);
        model.addAttribute("currtdate",currtdate);
        model.addAttribute("gid",gid);
        model.addAttribute("cost",cost);
        return "book_detailed";
    }

    /**
     * 查询我的预约场地
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/mybookground")
    public String myBookGround(Model model,HttpServletRequest request)throws Exception{

        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();

        List<GroundBookDTO> querymybook = groundBookService.querymybook(userId);
        List<GroundBookDTO> nouse = new ArrayList<>();
        List<GroundBookDTO> overtimebook = new ArrayList<>();
        Date current = new Date();
        for(int i=0;i<querymybook.size();i++){
            Date bendTime = querymybook.get(i).getBendTime();
            if(current.after(bendTime)){
                overtimebook.add(querymybook.get(i));
            }else {
                nouse.add(querymybook.get(i));
            }
        }



        model.addAttribute("overtimebooks",overtimebook);
        model.addAttribute("mybooks",nouse);
        return "my_book";


    }

    /**
     * 跳到公告编辑页面
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/tonoticeedit")
    public String toNoticeEdit(Model model) throws Exception{
        TbNotice getNotice = noticeService.get(1);
    if(getNotice != null){
        model.addAttribute("notice",getNotice);
    }
        return "notice";
    }

    /**
     * 查询公告
     * @param model
     * @return
     * @throws Exception
     */

    @GetMapping("/toquerynotice")
    public String toQueryNotice(Model model)throws Exception{
        TbNotice tbNotice = noticeService.get(1);
        model.addAttribute("notice",tbNotice);
        return "querynotice";
    }

    /**
     * 更新公告
     * @param tbNotice
     * @return
     * @throws Exception
     */
    @PostMapping("/updatenotice")
    public String updateNotice(TbNotice tbNotice) throws Exception{
        TbNotice currtNotice = noticeService.get(1);
        if(currtNotice != null){
            String nnotice = tbNotice.getNnotice();
            currtNotice.setNnotice(nnotice);
            noticeService.update(currtNotice);
        }
        noticeService.save(tbNotice);
        return "redirect:/tonoticeedit";
    }

    /**
     * 失约的场地预约处理,是否加入黑名单
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/torepudiation")
    public String toRepudiation(Model model)throws Exception{
        Date currentTime = new Date();
        List<GroundBook> allGroundBook = groundBookService.findAll();
        List<GroundBook> allaftergb = new ArrayList<>();
        List<GroundBookDTO> groundbookmessage = new ArrayList<>();
        for(int i=0;i<allGroundBook.size();i++){
            Date booldate = allGroundBook.get(i).getBendTime();
            System.out.println(booldate);
            boolean ifafter = currentTime.after(booldate);
            if(ifafter && allGroundBook.get(i).getBisuse() != "1"){
                GroundBookDTO groundBookDTO = new GroundBookDTO();
                groundBookDTO.setGGid(allGroundBook.get(i).getGGid());
                groundBookDTO.setUserid(allGroundBook.get(i).getUserid());
                groundBookDTO.setBid(allGroundBook.get(i).getBid());
                Ground ground = groundService.get(allGroundBook.get(i).getGGid());
                User user = userService.get(allGroundBook.get(i).getUserid());
                groundBookDTO.setGround(ground);
                groundBookDTO.setUser(user);
                groundbookmessage.add(groundBookDTO);
                System.out.println(groundBookDTO.toString());
            }
        }

        model.addAttribute("groundbookmessage",groundbookmessage);

        return "repudiation";
    }
    @GetMapping("/tobookbook{gid}")
    public String toBookBook(@PathVariable("gid") Integer gid,Model model){
        model.addAttribute("gidmessage",gid);
        return "book_book";
    }

    /**
     * 查看所有黑名单
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("toblack")
    public String toBlack(Model model)throws Exception{

        List<BlackDTO> listblack = repudiationService.listblack();
        model.addAttribute("black",listblack);
        return "deleteblack";
    }
    /**
     * 查看预约的场地信息
     */
    @GetMapping("querygroundbook")
    public String queryBook(HttpServletRequest request,Model model)throws Exception{
        User currenttuser = (User)request.getSession().getAttribute("user");
        Integer currenttuserUserId = currenttuser.getUserId();
        List<GroundBook> groundBooks = groundBookService.querybookByid(currenttuserUserId);
        model.addAttribute("groundBooks",groundBooks);
        return "querybookground";
    }
}
