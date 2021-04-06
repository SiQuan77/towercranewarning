function getCurDate()
{
    var d = new Date();
    var week;
    switch (d.getDay()){
        case 1: week="星期一"; break;
        case 2: week="星期二"; break;
        case 3: week="星期三"; break;
        case 4: week="星期四"; break;
        case 5: week="星期五"; break;
        case 6: week="星期六"; break;
        default: week="星期天";
    }
    var years = d.getFullYear();
    var month = add_zero(d.getMonth()+1);
    var days = add_zero(d.getDate());
    var hours = add_zero(d.getHours());
    var minutes = add_zero(d.getMinutes());
    var seconds=add_zero(d.getSeconds());
    var ndate = "<strong><h4>"+years+"年"+month+"月"+days+"日</br>"+hours+":"+minutes+":"+seconds+" </br>"+week+"</h4></strong>";
    var divT=document.getElementById("time");
    divT.innerHTML= ndate;
}
function showTime() {
    setInterval("getCurDate()",100);
}
function add_zero(temp)
{
    if(temp<10) return "0"+temp;
    else return temp;
}
function check() {

    if($.cookie('check')!=1){
        alert("请从登录页面进行访问！！")
        location.href="404.html"
    }
}
function loadmeetinginfo() {
    var mno=$("#mno").val();
    var re = /^[0-9]+.?[0-9]*$/;
    if (!re.test(mno)) {
        alert("请输入数字");
        $("#mno").val().value = "";
        return false;
    }
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/meeting/loadMeetingByNo.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'mno':mno
        },
        //成功之后
        success : function(data) {
            if(data!="该编号的会议不存在"){
                var html="<tr><td>"+mno
                    +"</td><td>"+data['rno']+"</td><td>"+data['order']+"</td><td>"+data['begin']
                    +"</td><td>"+data['end']+"</td><td>"+data['ano']+"</td></tr>"
                $("#meeting1").append(html)
            }else{
                alert(data)
            }

        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取会议数据失败", "error");
        }
    });
}

function Clearmeeting() {
    $("#meeting1").empty()
    var html="<tr><td>会议编号 </td><td>会议室编号</td><td>会议预定时间</td><td>会议开始时间</td><td>会议结束时间</td><td>会议发起者账号</td></tr>"
    $("#meeting1").append(html)
}

function Clearstaff() {
    $("#stafftable").empty()
    var html="<tr><td>工号</td><td>姓名</td><td>性别</td><td>年龄</td><td>所属部门</td><td>权限</td></tr>"
    $("#stafftable").append(html)
}
function loadRoominfo2() {
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/room/loadRoomInfo.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {},
        //成功之后
        success : function(data) {
            for(var  i=0;i<data.length;i++)
            {
                var html="<div class=\"col-md-4\">\n" +
                    "                        <div class=\"card p-5\"><div class='card-body d-flex justify-content-between align-items-center'><div><span class='h4 d-block font-weight-normal mb-3'>会议室编号："
                    +data[i]['rno']+"</span> <span class='font-weight-light'>会议室规模："+
                    data[i]['size']+ "</span></br> <span class='font-weight-light'>会议室可容纳人数："+data[i]['capacity']+
                    "</span></br> <span class='font-weight-light'id='s"+data[i]['rno']+"'>会议室是否空闲：</span></br></div> <div class='h2 text-muted'> <i class='icon icon-people'></i> </div> </div> </div>\n" +
                    "                    </div>"
                $("#meetingroominfo1").append(html);
            }
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取会议室数据失败", "error");
        }
    });

    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/room/loadRoomStatus.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {},
        //成功之后
        success : function(data) {
            for(var key in data)
            {
                if(data[key]=='Y') {
                    $("#s" + key).append("Y")
                }else{
                    $("#s" + key).append("N")
                    $("#s"+key).parent().parent().find(".text-muted").css("background-color","#FF6666")
                }
            }
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取会议室数据失败", "error");
        }
    });

}


function loadRoomInfo(){
    //调用remove函数清空下拉列表
    //状态码
    //使用ajax请求json数据
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/room/loadRoomInfo.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {},
        //成功之后
        success : function(data) {
            for(var  i=0;i<data.length;i++)
            {
                var html="<div class=\"col-md-4\">\n" +
                    "                        <div class=\"card p-5\"><div class='card-body d-flex justify-content-between align-items-center'><div><span class='h4 d-block font-weight-normal mb-3'>会议室编号："
                    +data[i]['rno']+"</span> <span class='font-weight-light'>会议室规模："+
                    data[i]['size']+ "</span></br> <span class='font-weight-light'>会议室可容纳人数："+data[i]['capacity']+
                    "</span></br> <span class='font-weight-light'id='s"+data[i]['rno']+"'>会议室是否空闲：</span></br></div> <div class='h2 text-muted'> <i class='icon icon-people'></i> </div> </div> </div>\n" +
                    "                    </div>"
                $("#meetingroominfo").append(html);
            }
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取会议室数据失败", "error");
        }
    });

    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/room/loadRoomStatus.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {},
        //成功之后
        success : function(data) {
            for(var key in data)
            {
                if(data[key]=='Y') {
                    $("#s" + key).append("Y")
                }else{
                    $("#s" + key).append("N")
                    $("#s"+key).parent().parent().find(".text-muted").css("background-color","#FF6666")
                }
            }
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取会议室数据失败", "error");
        }
    });
}

function loadstaffinfo() {
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/staff/loadStaffBySNo",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'sno':$.cookie('ano')
        },
        //成功之后
        success : function(data) {
            $("#spanname").append(data['name'])
            $("#spanage").append(data['age'])
            $("#spangender").append(data['gender'])
            $("#spansno").append($.cookie('sno'))
            $("#spandept").append(data['dept'])
            $("#spanname2").append(data['name'])
            $.cookie("name",data['name'])
            if($.cookie("admin")==2){
                $("#spanadmin").append("管理员")
            }else{
                $("#spanadmin").append("非管理员")
            }
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取个人信息失败", "error");
        }
    });
}

function loadstafftable() {
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/staff/loadStaffBySno",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'sno':$("#sno2").val()
        },
        //成功之后
        success : function(data) {
            if(data=="该编号的员工不存在"){
                alert(data);
            }else{
                var html="<tr><td>"+$("#sno2").val()
                    +"</td><td>"+data['name']+"</td><td>"+data['gender']+"</td><td>"
                    +data['age']+"</td><td>"+data['dept']+"</td></tr>"
                $("#stafftable").append(html)
            }
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取个人信息失败", "error");
        }
    });
}
function submitMeeting() {
    //获取items_selected中的所有值
    var staffs = new Array();
    var index  = 0;

    $("#items_selected option").each(function(){
        staffs[index++] = $(this).text();
        alert($(this).text())
    })
    for(var i=0;i<staffs.length;i++){
        alert(staffs[i]);
    }

    //获取会议室编号
    var roomNumber = $("#meetingroom option:selected").text();
    alert("roomnumber是"+roomNumber)
    //获取开会时间
    var date = $("#meetingtime").val()
    alert(date);
    //获取持续时间
    var time = $("#meeting_hour").find("option:selected").text()+ '小时' + $("#meeting_minute").find("option:selected").text() + '分钟';
    alert(time)

}


function loadmeeting() {
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/meeting/loadMeetingBatch.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'first':0,
            'batch':10
        },
        //成功之后
        success : function(data) {
            $.cookie("meetingfirst",0)
            for(var i=0;i<data.length;i++){
                var html="<tr><td>"+data[i]['mno']+"</td><td>"+data[i]['rno']+"</td><td>"+data[i]['order']
                    +"</td><td>"+data[i]['begin']+"</td><td>"+data[i]['end']+"</td><td>"+data[i]['total']
                    +"</td><td>"+data[i]['signIn']+"</td><td>"+data[i]['ano']+"</td></tr>"
                $("#meeting").append(html)
            }
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取所有会议信息失败", "error");
        }
    });
    $("#start_loading").css("display","none")
    $("#previous_text").css("margin-left","60%")
}

function nextmeeting() {
    var first=$.cookie("meetingfirst")+10
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/meeting/loadMeetingBatch.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'first':first,
            'batch':10
        },
        //成功之后
        success : function(data) {
            if(data.length==0){
                alert("无再多会议记录！")
                return
            }
            $("#meeting").empty();
            var html1="<tr>\n" +
                "<td>会议编号</td>\n" +
                "<td>会议室编号</td>\n" +
                "<td>会议预定时间</td>\n" +
                "<td>会议开始时间</td>\n" +
                "<td>会议结束时间</td>\n" +
                "<td>总人数</td>\n" +
                "<td>已签到人数</td>\n" +
                "<td>会议发起者账户</td>\n" +
                "</tr>"
            $("#meeting").append(html1)
            for(var i=0;i<data.length;i++){
                var html="<tr><td>"+data[i]['mno']+"</td><td>"+data[i]['rno']+"</td><td>"+data[i]['order']+"</td><td>"+data[i]['begin']+"</td><td>"+data[i]['end']+"</td><td>"+data[i]['total']+"</td><td>"+data[i]['signIn']+"</td><td>"+data[i]['ano']+"</td></tr>"
                $("#meeting").append(html)
            }
            $.cookie("meetingfirst",first)
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取所有会议信息失败", "error");
        }
    });
}


function previousmeeting() {
    if($.cookie("meetingfirst")==0){
        alert("已经是第一页了！")
        return
    }
    var first=$.cookie("meetingfirst")-10
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/meeting/loadMeetingBatch.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'first':first,
            'batch':10
        },
        //成功之后
        success : function(data) {
            if(data.length==0){
                alert("无再多会议记录！")
                return
            }
            $("#meeting").empty();
            var html1="<tr>\n" +
                "<td>会议编号</td>\n" +
                "<td>会议室编号</td>\n" +
                "<td>会议预定时间</td>\n" +
                "<td>会议开始时间</td>\n" +
                "<td>会议结束时间</td>\n" +
                "<td>总人数</td>\n" +
                "<td>已签到人数</td>\n" +
                "<td>会议发起者账户</td>\n" +
                "</tr>"
            $("#meeting").append(html1)
            for(var i=0;i<data.length;i++){
                var html="<tr><td>"+data[i]['mno']+"</td><td>"+data[i]['rno']+"</td><td>"+data[i]['order']+"</td><td>"+data[i]['begin']+"</td><td>"+data[i]['end']+"</td><td>"+data[i]['total']+"</td><td>"+data[i]['signIn']+"</td><td>"+data[i]['ano']+"</td></tr>"
                $("#meeting").append(html)
            }
            $.cookie("meetingfirst",first)
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取所有会议信息失败", "error");
        }
    });
}

function loadmeetingroom_by_mno() {
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/meeting/loadMeetingByRno",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'rno':$("#rno2").val()
        },
        //成功之后
        success : function(data) {
            if(data=="查询失败"){
                alert(data);
                return;
            }
            if(data.length==0){
                alert("暂无与该会议室相关的会议！");
                return;
            }else{
                for(var i=0;i<data.length;i++){
                    var html="<tr><td>"+data[i]['rno']+"</td><td>"+data[i]['mno']+"</td><td>"+data[i]['order']+"</td><td>"+data[i]['begin']+"</td><td>"+data[i]['end']+"</td><td>"+data[i]['total']+"</td><td>"+data[i]['signIn']+"</td><td>"+data[i]['ano']+"</td></tr>"
                    $("#roomtable2").append(html);
                }
            }


        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取会议室会议信息失败", "error");
        }
    });
}

function Clearmeetingroom() {
    $("#roomtable2").empty()
    var html="<tr>\n" +
        "                                        <td>会议室编号</td>\n" +
        "                                        <td>会议编号</td>\n" +
        "                                        <td>预定时间</td>\n" +
        "                                        <td>开始时间</td>\n" +
        "                                        <td>结束时间</td>\n" +
        "                                        <td>总人数</td>\n" +
        "                                        <td>已签到人数</td>\n" +
        "                                        <td>发起者账号</td>\n" +
        "                                    </tr>"
    $("#roomtable2").append(html)
}


function load_meeting_with_me_start() {
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/meeting/loadMeetingByAno",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'ano':$.cookie("ano")
        },
        //成功之后
        success : function(data) {
           if(data.length==0){
               alert("暂无与您相关的会议！");
               return;
           }else{
               for(var i=0;i<data.length;i++){
                   var html="<tr><td>"+data[i]['mno']+"</td><td>"+data[i]['rno']+"</td><td>"+data[i]['order']
                       +"</td><td>"+data[i]['begin']+"</td><td>"+data[i]['end']+"</td><td>"+data[i]['ano']
                       +"</td><td>"+data[i]['total']+"</td><td>"+data[i]['signIn']+"</td></tr>"
                   $("#meeting_with_me_start").append(html);
               }
           }


        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取所有会议信息失败", "error");
        }
    });



}


function submitMeetingroom() {
    if($.cookie("check_1")!=1||$.cookie("check_2")!=1){
        alert("请按要求调整数据后再提交！");
        return;
    }
    var rno=document.getElementById("check_1").value;
    var cap=$("#check_2").val();
    var size;
    if(size<70){
        size="small"
    }else if(size<120){
        size="mid"
    }else{
        size="big"
    }
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/room/addMeetingRoom.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'rno':rno,
            'capacity':cap,
            'size':size
        },
        //成功之后
        success : function(data) {
            alert(data);
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("因不明原因，添加会议失败", "error");
        }
    });


}


function logout() {
    alert(window.localStorage.tokenId);
}

function loadmeeting_with_me_join() {
        $.ajax({
            //请求类型为post
            type : "POST",
            //请求链接
            url : "http://39.108.186.137:8080/MeetingRoom/meeting/loadMeetingBySno",
            //request数据类型
            dataType : "json",
            //数据
            data : {
                'sno':$.cookie("sno")
            },
            //成功之后
            success : function(data) {
                if(data.length==0){
                    alert("暂无与您相关的会议！");
                    return;
                }else{
                    for(var i=0;i<data.length;i++){
                        var html="<tr><td>"+data[i]['mno']+"</td><td>"+data[i]['rno']+"</td><td>"+data[i]['order']+"</td><td>"
                            +data[i]['begin']+"</td><td>"+data[i]['end']+"</td><td>"+data[i]['ano']+"</td><td>"+data[i]['total']+"</td><td>"
                            +data[i]['signIn']+"</td></tr>"
                        $("#meeting_with_me_join").append(html);
                    }
                }


            },
            //错误处理
            error : function() {
                //弹出警告
                alert("获取所有会议信息失败", "error");
            }
    });
}

function check_in_addmeetingroom() {
    var reg = new RegExp("^[0-9]*$");
    var h1=$("#check_1").val();


    if(reg.test(h1)&&h1.length==3){
        $("#check_1_r").css("display","inline");
        $("#check_1_w").css("display","none");
        $("#check_tip").css("display","none");
        $.cookie("check_1",1);
    }else{
        $("#check_1_r").css("display","none");
        $("#check_1_w").css("display","inline");
        $("#check_tip").css("display","inline")
        $.cookie("check_1",0);
    }
}

function check_in_addmeetingroom2() {
    var reg = new RegExp("^[0-9]*$");
    var h1=$("#check_2").val();


    if(reg.test(h1)&&h1<=150&&h1>=30){
        $("#check_2_r").css("display","inline");
        $("#check_2_w").css("display","none");
        $("#check_tip2").css("display","none")
        $.cookie("check_2",1);
        if(h1>=30&&h1<=70){
            $("#meetingroom_size").empty();
            $("#meetingroom_size").append("Small")
        }else if(h1>=71&&h1<=120){
            $("#meetingroom_size").empty()
            $("#meetingroom_size").append("Mid")
        }else{
            $("#meetingroom_size").empty()
            $("#meetingroom_size").append("Big")
        }
    }else{
        $("#check_2_r").css("display","none");
        $("#check_2_w").css("display","inline");
        $("#check_tip2").css("display","inline")
        $.cookie("check_2",0);
    }
}

function load_room_can_delete() {
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/room/loadRoomCanDelete.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {},
        //成功之后
        success : function(data) {
            for(var i=0;i<data.length;i++){
                $("#delete_meetingroom_table").append("<option>"+data[i]+"</option>");
            }

        },
        //错误处理
        error : function() {
            //弹出警告
            alert("获取可删除会议室数据失败", "error");
        }
    });
}

function delete_meetingroom() {
    $.ajax({
        //请求类型为post
        type : "POST",
        //请求链接
        url : "http://39.108.186.137:8080/MeetingRoom/room/deleteRoom.action",
        //request数据类型
        dataType : "json",
        //数据
        data : {
            'rno': $("#delete_meetingroom_table").find("option:selected").text()
        },
        //成功之后
        success : function(data) {
            alert(data)
            location.reload();
        },
        //错误处理
        error : function() {
            //弹出警告
            alert("删除失败", "error");
        }
    });
}

function reset_meetingroom_option() {
    $("#check_1").val(" ");
    $("#check_2").val(" ");
    check_in_addmeetingroom();
    check_in_addmeetingroom2()
}