function show(sn) {
    $("#"+sn).modal('show');
}

function mousePosition(ev){
    if(ev.pageX || ev.pageY){
        return {x:ev.pageX, y:ev.pageY};
    }
    return {
        x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
        y:ev.clientY + document.body.scrollTop - document.body.clientTop
    };
}


function mouseDown(ev){
    ev = ev || window.event;
    var mousePos = mousePosition(ev);
    document.getElementById('xxx').value = mousePos.x;
    document.getElementById('yyy').value = mousePos.y;
}

document.onmousedown=mouseDown;

function Show(el){
    var x = parseInt(document.getElementById('xxx').value)-el.offsetLeft;
    var y = parseInt(document.getElementById('yyy').value)-el.offsetTop;
    document.getElementById("x_position").value=x-288;
    document.getElementById("y_position").value=y-93;


    // alert(x+","+y);


}


function dingwei1(){
    var select=document.getElementById("select_dingwei");
    if(select==null){
        alert("当前无设备可以添加！");
        return
    }

    if(select.options.length===0){
        alert("当前无设备可以添加！");
        return
    }
    document.getElementById("dingwei").style.display="";//显
    document.getElementById("button_dingwei").style.display="none";//显
    document.getElementById("flag").value="1";//将flag置于1表示开始进行在地图上添加设备的操作

}