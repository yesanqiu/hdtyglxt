package com.example.hdtyglxt.controller;

import com.example.hdtyglxt.base.dto.ResultDTO;
import com.example.hdtyglxt.base.util.ResultUtil;
import com.example.hdtyglxt.entity.Ground;
import com.example.hdtyglxt.entity.GroundBook;
import com.example.hdtyglxt.entity.TbRepudiation;
import com.example.hdtyglxt.entity.User;
import com.example.hdtyglxt.service.GroundBookService;
import com.example.hdtyglxt.service.GroundService;
import com.example.hdtyglxt.service.NoticeService;
import com.example.hdtyglxt.service.RepudiationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class GroundController {

    @Autowired
    private GroundService groundService;
    @Autowired
    private GroundBookService groundBookService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private RepudiationService repudiationService;

    @GetMapping("/listground")
    public ResultDTO getAllground(@RequestParam(required = true,defaultValue = "1") Integer pageNum,
                                  @RequestParam(required = true,defaultValue = "3")Integer pageSize) throws Exception {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有场地的方法
        List<Ground> allgrounds = groundService.findAll();
        PageInfo<Ground> pageInfo = new PageInfo<>(allgrounds);
        if(null == pageInfo){
            return ResultUtil.Error("111","查询数据失败");
        }else {
            return ResultUtil.Success(pageInfo);
        }
    }

    /**
     * 删除场地
     * @param gid
     * @return
     * @throws Exception
     */
    @GetMapping("/deletebyid")
    public ResultDTO deleteById(Integer gid) throws Exception{
        Ground ground = new Ground();
        ground.setGid(gid);
        groundService.delete(ground);
        return ResultUtil.Success();
    }

    /**
     * 修改状态
     * @param gid
     * @param gstatus
     * @return
     * @throws Exception
     */
    @GetMapping("/setstatus")
    public ResultDTO setStatus(Integer gid,String gstatus) throws Exception{
        Ground ground = groundService.get(gid);
        ground.setGstatus(gstatus);
        groundService.update(ground);
        return ResultUtil.Success();
    }
    /**
     * 场地预约
     * @param gid
     * @param bookdate
     * @param bookst
     * @param booked
     * @param cost
     * @return
     * @throws Exception
     */
    @GetMapping("/bookground")
    public ResultDTO bookGround(@RequestParam("gid")String gid,
                                @RequestParam("bookdate")String bookdate,
                                @RequestParam("bookst")String bookst,
                                @RequestParam("booked")String booked,
                                @RequestParam("cost")String cost,
                                HttpServletRequest request) throws Exception{
        String bookdatestarttime = bookdate+" "+bookst;
        String bookdateendtime = bookdate+" "+booked;
        int id = Integer.parseInt(gid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timetart = sdf.parse(bookdatestarttime);
        Date timesend = sdf.parse(bookdateendtime);
        Date currttime = new Date();

        GroundBook findgroundBook = new GroundBook();
        findgroundBook.setGGid(id);
        List<GroundBook> hasgroundbook = groundBookService.findByParams(findgroundBook);
        for(int i = 0;i<hasgroundbook.size();i++){
            Date bstartTime = hasgroundbook.get(i).getBstartTime();
            Date bendTime = hasgroundbook.get(i).getBendTime();
            if(timetart.compareTo(bstartTime)!=0 && timesend.compareTo(bendTime) !=0) {
                if (timetart.after(bstartTime) && timesend.before(bendTime)) {
                    return ResultUtil.Error("100", "当前时间已被预约");
                }
            }
            if(timetart.compareTo(bstartTime)==0 && timesend.compareTo(bendTime) ==0){
                return ResultUtil.Error("100", "当前时间已被预约");
            }
            if(timetart.compareTo(bstartTime)==0){
                if(timesend.before(bendTime)){
                    return ResultUtil.Error("100", "当前时间已被预约");
                }
            }
            if(timesend.compareTo(bendTime)==0){
                if(timetart.after(bstartTime)){
                    return ResultUtil.Error("100", "当前时间已被预约");
                }
            }
        }
        boolean ifovertime = timetart.before(currttime);
        if(ifovertime){
            return ResultUtil.Error("100","已超过时间，不能预约");
        }
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        BigDecimal totalcost = new BigDecimal(cost);
        GroundBook savebook = new GroundBook(id,null,timetart,timesend,userId,totalcost,"0");
        groundBookService.save(savebook);
        return ResultUtil.Success("预约成功");
    }

    /**
     * 搜索场地
     * @param gtype
     * @return
     * @throws Exception
     */
    @GetMapping("/querygroundbyparms")
    public ResultDTO queryGroundByParms(String gtype) throws Exception{
        Ground ground = new Ground();
        ground.setGtype(gtype);
        List<Ground> groundsbytype = groundService.findByParams(ground);
        if(groundsbytype.size() == 0){
            ResultUtil.Error("100","没有相应的数据，请重新输入");
        }
        return ResultUtil.Success(groundsbytype);
    }

    /**
     * 预约退订
     * @param bid
     * @return
     * @throws Exception
     */
    @GetMapping("/bookdelete")
    public ResultDTO bookDelete(@RequestParam("bid") Integer bid)throws Exception{
        GroundBook groundBook = new GroundBook();
        groundBook.setBid(bid);
        groundBookService.delete(groundBook);
        return ResultUtil.Success();
    }

    /**
     * 加入黑名单
     * @return
     * @throws Exception
     */
    @PostMapping("/addblack")
    public ResultDTO addBlack(@RequestParam("userid")Integer userid)throws Exception{
        List<TbRepudiation> allrepudiation = repudiationService.findAll();
        for(int i=0;i<allrepudiation.size();i++){
            if(userid == allrepudiation.get(i).getRUserid()){
                return ResultUtil.Error("100","当前用户已加入黑名单");
            }
        }
        TbRepudiation tbRepudiation = new TbRepudiation();
        tbRepudiation.setRUserid(userid);
        repudiationService.save(tbRepudiation);
        return ResultUtil.Success();
    }

    /**
     * 场地预留
     * @param gid
     * @param startdate
     * @param starttime
     * @param enddate
     * @param endtime
     * @return
     * @throws Exception
     */
    @GetMapping("bookbook")
    public ResultDTO bookBook(@RequestParam("gid") Integer gid,
                              @RequestParam("startdate") String startdate,
                              @RequestParam("starttime") String starttime,
                              @RequestParam("enddate") String enddate,
                              @RequestParam("endtime") String endtime,
                              HttpServletRequest request)throws Exception{
        String bookdatestarttime = startdate+" "+starttime;
        String bookdateendtime = enddate+" "+endtime;
        System.out.println(bookdatestarttime+bookdateendtime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //预约开始时间
        Date timetart = sdf.parse(bookdatestarttime);
        //预约结束时间
        Date timeend = sdf.parse(bookdateendtime);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, + 7);
        Date time = c.getTime();
        String format = sdf.format(time);
        System.out.println(format);
        if(timetart.after(timeend)){
            return ResultUtil.Error("100","输入时间冲突，请重新输入");
        }
        if(timetart.before(time)){
            return ResultUtil.Error("100","输入时间冲突，只能预留一周之后");
        }
        if(timeend.before(time)){
            return ResultUtil.Error("100","输入时间冲突，只能预留一周之后");
        }

        Calendar nowstart = Calendar.getInstance();
        Calendar nowend = Calendar.getInstance();
        nowstart.setTime(timetart);
        int i = nowstart.get(Calendar.DAY_OF_WEEK);
        System.out.println(i);
        if(i == 7 || i == 1){
            return ResultUtil.Error("100","开始时间为星期六日，不接受预留");
        }
        nowend.setTime(timeend);
        int j = nowend.get(Calendar.DAY_OF_WEEK);
        if(j == 7 || j == 1){
            return ResultUtil.Error("100","结束时间为星期六日，不接受预留");
        }
        GroundBook findgroundBook = new GroundBook();
        findgroundBook.setGGid(gid);
        List<GroundBook> hasgroundbook = groundBookService.findByParams(findgroundBook);
        for(int k = 0;k<hasgroundbook.size();k++){
            Date bstartTime = hasgroundbook.get(k).getBstartTime();
            Date bendTime = hasgroundbook.get(k).getBendTime();
            if(timetart.compareTo(bstartTime)!=0 && timeend.compareTo(bendTime) !=0) {
                if(timetart.before(bstartTime) && timeend.after(timetart)){
                    return ResultUtil.Error("100", "结束时间已被预留");
                }
                if(timetart.before(bendTime) && timeend.after(bstartTime)){
                    return ResultUtil.Error("100", "开始时间已被预留");
                }
                if(timetart.before(bstartTime) && timeend.after(bendTime)){
                    return ResultUtil.Error("100", "时间段内已被预留");
                }
                if (timetart.after(bstartTime) && timeend.before(bendTime)) {
                    return ResultUtil.Error("100", "当前时间已被预留");
                }

            }
            if(timetart.compareTo(bstartTime)==0 && timeend.compareTo(bendTime) ==0){
                return ResultUtil.Error("100", "当前时间已被预留");
            }
            if(timetart.compareTo(bstartTime)==0){
                if(timeend.before(bendTime)){
                    return ResultUtil.Error("100", "当前时间已被预留");
                }
            }
            if(timeend.compareTo(bendTime)==0){
                if(timetart.after(bstartTime)){
                    return ResultUtil.Error("100", "当前时间已被预留");
                }
            }
        }
        GroundBook groundBook = new GroundBook();
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        groundBook.setUserid(userId);
        groundBook.setGGid(gid);
        groundBook.setBstartTime(timetart);
        groundBook.setBendTime(timeend);
        BigDecimal totalcost = new BigDecimal("00.00");
        groundBook.setBcost(totalcost);
        groundBook.setBisuse("0");
        groundBookService.save(groundBook);
        return ResultUtil.Success();
    }

    /**
     * 删除黑名单
     * @param rid
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteblack")
    public ResultDTO deleteBlack(@RequestParam("rid")Integer rid)throws Exception{
        repudiationService.deleteById(rid);
        return ResultUtil.Success();
    }
}


