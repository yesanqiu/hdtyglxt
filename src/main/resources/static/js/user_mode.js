$(document).ready(function () {

   loadMsg();
   var userPageNum = 1;
   var adminUserPageNum = 1;
   loadUser(userPageNum);
   loadAdminUser(adminUserPageNum);
    $(document).on('click','.to_index',function () {
        window.location.href = "index.html"
    });
    $(document).on('click','.item',function () {
        if(!$(this).hasClass("active_item")){
            var index = $(this).index();
            $(this).addClass("active_item");
            $(this).siblings().removeClass("active_item");
            if(index == 1){
                reloadUser(1);
            }
            if(index == 3){
                reloadAdminUser(1);
            }
            $('.c_b_right').children().eq(index).css('display','flex');
            $('.c_b_right').children().eq(index).siblings().hide();
        }
    });
    $(document).on('click','.addUser',function () {
        var username = $(".psw input[ name='username' ] ").val();
        var nickname = $(".psw input[ name='nickname' ] ").val();
        var studentId = $(".psw input[ name='studentId' ] ").val();
        var password = $(".psw input[ name='password' ] ").val();
        param = {};
        param.username = username;
        param.nickname = nickname;
        param.studentId = studentId;
        param.password = password;
        if(nickname == null || username == null || studentId == null || password ==null){
            alert("信息不完整")
        }else {
            $.ajax({
                type: 'post',
                url: 'http://localhost:8579/admin/addUser',
                data: param,
                success: function (data) {
                    if(data.code ==="200"){
                        alert("添加成功")
                    }else{
                        alert(data.errmsg);
                    }
                }
            });
        }
    });
    $(document).on('click','.addAdmin',function () {
        var username = $(" input[ name='adminusername' ] ").val();
        var nickname = $(" input[ name='adminnickname' ] ").val();
        var studentId = $(" input[ name='adminstudentId' ] ").val();
        var password = $(" input[ name='adminpassword' ] ").val();
        param = {};
        param.username = username;
        param.nickname = nickname;
        param.studentId = studentId;
        param.password = password;
        if(nickname == null || username == null || studentId == null || password ==null){
            alert("信息不完整")
        }else {
            $.ajax({
                type: 'post',
                url: 'http://localhost:8579/admin/addAdminUser',
                data: param,
                success: function (data) {
                    if(data.code ==="200"){
                        alert("添加成功")
                    }
                }
            });
        }
    })

    $(document).on('click','.users .delBtn',function (e) {
        var userId = e.target.id;
        $.ajax({
            type: 'delete',
            url: 'http://localhost:8579/admin/deleteUser?userId=' + userId,
            data: param,
            success: function (data) {
                reloadUser(userPageNum);
                alert("删除成功");
            }
        });
    })
    $(document).on('click','.admin_users .delBtn',function (e) {
        var userId = e.target.id;
        $.ajax({
            type: 'delete',
            url: 'http://localhost:8579/admin/deleteAdminUser?userId='+userId,
            success: function (data) {
                reloadAdminUser(adminUserPageNum);
                alert("删除成功");
            }
        });
    })

    $(document).on('click','.prev_users',function () {
        if(userPageNum == 0){
            alert("没有上一页了");
        }else {
            userPageNum--;
            reloadUser(userPageNum)
        }
    })

    $(document).on('click','.next_users',function () {
        userPageNum++;
        reloadUser(userPageNum)
    })

    $(document).on('click','.prev_admin_users',function () {
        if(adminUserPageNum == 0){
            alert("没有上一页了");
        }else {
            adminUserPageNum--;
            reloadUser(adminUserPageNum)
        }
    })

    $(document).on('click','.next_admin_users',function () {
        adminUserPageNum++;
        reloadUser(adminUserPageNum)
    })
});

function loadMsg() {
    $.ajax({
        type: 'GET',
        url: "http://localhost:8579/user/getUserMsg",
        success: function (data) {
            if(data.code == "200"){
                $('.nickname').text(data.data.nickname);
            }
        }
    });
}

function loadUser(pageUnm) {
    param = {};
    param.pageSize = 8;
    param.pageNum = pageUnm;
    $.ajax({
        type: 'GET',
        url: "http://localhost:8579/admin/getUserLimit",
        data: param,
        success: function (data) {
            if(data.code == "200"){
                data.data.data.forEach(function (val) {
                    $('.users').append(" <div class=\"t_line\">\n" +
                        "                            <div class=\"t_item\">"+val.nickname + "</div>\n" +
                    "                            <div class=\"t_item\">"+val.username+"</div>\n" +
                    "                            <div class=\"t_item\">"+val.studentId+"</div>\n" +
                    "                            <button class=\"delBtn\" id="+ val.userId +">删除</button>\n" +
                    "                        </div>");
                })
                

            }
        }
    });
}
function reloadUser(pageNum) {
    param = {};
    param.pageSize = 8;
    param.pageNum = pageNum;
    $.ajax({
        type: 'GET',
        url: "http://localhost:8579/admin/getUserLimit",
        data: param,
        success: function (data) {
            if(data.code == "200"){
                if(data.data.totalPages < pageNum){
                    pageNum --;
                    alert("没有下一页了")
                }else {
                    $('.users').html('');
                    data.data.data.forEach(function (val) {
                        $('.users').append(" <div class=\"t_line\">\n" +
                            "                            <div class=\"t_item\">" + val.nickname + "</div>\n" +
                            "                            <div class=\"t_item\">" + val.username + "</div>\n" +
                            "                            <div class=\"t_item\">" + val.studentId + "</div>\n" +
                            "                            <button class=\"delBtn\" id=" + val.userId + ">删除</button>\n" +
                            "                        </div>");
                    })
                }


            }
        }
    });
}
function loadAdminUser(pageNum) {
    param = {};
    param.pageSize = 8;
    param.pageNum = pageNum;
    $.ajax({
        type: 'GET',
        url: "http://localhost:8579/admin/getAdminUserLimit",
        data: param,
        success: function (data) {
            if(data.code == "200"){
                data.data.data.forEach(function (val) {
                    $('.admin_users').append(" <div class=\"t_line\">\n" +
                        "                            <div class=\"t_item\">"+val.nickname + "</div>\n" +
                        "                            <div class=\"t_item\">"+val.username+"</div>\n" +
                        "                            <div class=\"t_item\">"+val.studentId+"</div>\n" +
                        "                            <button class=\"delBtn\" id="+ val.userId +">删除</button>\n" +
                        "                        </div>");
                })


            }
        }
    });
}
function reloadAdminUser(pageNum) {
    param = {};
    param.pageSize = 8;
    param.pageNum = pageNum;
    $.ajax({
        type: 'GET',
        url: "http://localhost:8579/admin/getAdminUserLimit",
        data: param,
        success: function (data) {
            if(data.code == "200") {
                if (data.data.totalPages < pageNum) {
                    pageNum--;
                    alert("没有下一页了");
                } else {
                    $('.admin_users').html('');
                    data.data.data.forEach(function (val) {
                        $('.admin_users').append(" <div class=\"t_line\">\n" +
                            "                            <div class=\"t_item\">" + val.nickname + "</div>\n" +
                            "                            <div class=\"t_item\">" + val.username + "</div>\n" +
                            "                            <div class=\"t_item\">" + val.studentId + "</div>\n" +
                            "                            <button class=\"delBtn\" id=" + val.userId + ">删除</button>\n" +
                            "                        </div>");
                    })
                }
            }


        }
    });
}