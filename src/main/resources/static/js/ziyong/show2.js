var type_of_equip;
var sn;
var id_of_interval;


var sn_sjj;
var id_of_interval2;
function getData(type){
    var select=document.getElementById("taji_to_be_selected");
    var index=select.selectedIndex;
    sn=select.options[index].value;
    type_of_equip=type;
    ajax();
    id_of_interval=setInterval("ajax()",15000);

    document.getElementById("start_moni").style.display="none";
    document.getElementById("stop_moni").style.display="block";

}


function ajax(){
    $.ajax({
        type: "post",
        url: "/show/getData",    //向后端请求数据的url
        data: {
            "sn":sn,
            "type":type_of_equip
        },
        success: function (data) {
            document.getElementById("zaizhong").innerHTML="载重:"+data.taji_working.loading_payload;
            document.getElementById("zuida_zaizhong").innerHTML="最大载重:"+data.taji_working.maximum_payload;
            document.getElementById("range").innerHTML="幅度:"+data.taji_working.range_size;
            document.getElementById("height").innerHTML="高度:"+data.taji_working.height;
            document.getElementById("moment").innerHTML="力矩:"+data.taji_working.moment;
            document.getElementById("start_time").innerHTML="起吊时间:"+data.taji_working.starting_time;
            document.getElementById("end_time").innerHTML="落吊时间:"+data.taji_working.ending_time;
        }
    });
}

function stop_moni(){
    clearInterval(id_of_interval);
    document.getElementById("start_moni").style.display="block";
    document.getElementById("stop_moni").style.display="none";
}

function getData2(){
    var select=document.getElementById("sjj_to_be_selected");
    var index=select.selectedIndex;
    sn_sjj=select.options[index].value;

    ajax2();
    id_of_interval2=setInterval("ajax2()",15000);

    document.getElementById("start_moni2").style.display="none";
    document.getElementById("stop_moni2").style.display="block";
}

function ajax2(){
    $.ajax({
        type: "post",
        url: "/show/getData2",    //向后端请求数据的url
        data: {
            "sn":sn_sjj,
        },
        success: function (data) {
            document.getElementById("sjj_height").innerHTML="高度:"+data.height;
            if(data.lock_condition===0){
                document.getElementById("lock_conditon").innerHTML="未上锁";
            }else {
                document.getElementById("lock_conditon").innerHTML="已上锁";
            }
            document.getElementById("sjj_tilit").innerHTML="倾斜程度："+data.tilt_range+"°";
            document.getElementById("working_time").innerHTML=data.working_time;
        }
    });
}

function stop_moni2(){
    clearInterval(id_of_interval2);
    document.getElementById("start_moni2").style.display="block";
    document.getElementById("stop_moni2").style.display="none";
}