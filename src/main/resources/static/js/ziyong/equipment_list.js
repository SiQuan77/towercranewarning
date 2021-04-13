function add_equip(){
    $('#addModalId').modal('show');
}

function delete_equip(sn) {
    var msg="您真的要删除sn号为"+sn+"的设备吗？\n\n请确认！"

    if(confirm(msg)===true){
        $.post(
            "/equip/delete",
            {"sn":sn},
            function (response){
                console.log(response);
                // if(response.success){
                //     alert("删除成功！");
                // }else {
                //     alert("删除失败！");
                // }

            },
            "json"
        )
        alert("删除成功！")
        location.reload();
    }else {

    }
}


function bianji_equip(sn) {
    $('#bianjiModalId').modal('show');
    var tb = document.getElementById("equip_table");  //根据id找到这个表格
    var rows = tb.rows;               //取得这个table下的所有行

    var equipname;
    var equipxinxihao;
    var type;
    var equip_cqdw;
    var xinyongdaima;
    var install_dw;
    var install_time;
    var dismantle_dw;
    var dismantle_time;


    for (var i = 0; i < rows.length; i++)//循环遍历所有的tr行
    {
        for (var j = 0; j < rows[i].cells.length; j++)//取得第几行下面的td个数，再次循环遍历该行下面的td元素
        {
            // var cell = rows[i].cells[j];//获取某行下面的某个td元素
            // console.log("第" + (i + 1) + "行第" + (j + 1) + "格的数字是" + cell.innerHTML);//cell.innerHTML获取元素里头的值
            if (rows[i].cells[0].innerText === sn) {
                equipname = rows[i].cells[1].innerText;
                equipxinxihao = rows[i].cells[2].innerText;
                type = rows[i].cells[3].innerText;
                equip_cqdw = rows[i].cells[4].innerText;
                xinyongdaima = rows[i].cells[5].innerText;
                install_dw = rows[i].cells[6].innerText;
                install_time = rows[i].cells[7].innerText;
                dismantle_dw = rows[i].cells[8].innerText;
                dismantle_time = rows[i].cells[9].innerText;
            }
        }

    }

    $('#tanchaung_sn').val(sn);
    $('#tanchuang_name').val(equipname);
    $('#tanchuang_xinxihao').val(equipxinxihao);
    $('#tanchuang_type').val(type);
    $('#tanchuang_sbcqdw').val(equip_cqdw);
    $('#tanchuang_xinyongdaima').val(xinyongdaima);
    $('#tanchuang_azdw').val(install_dw);
    $('#tanchuang_installtime').val(install_time);
    $('#tanchuang_ccdw').val(dismantle_dw);

    //判断拆除时间是否为无，若为无则进行处理
    if (dismantle_time === '无') {
        $('#tanchuang_dismantletime').val("1900-01-01");

    } else {
        $('#tanchuang_dismantletime').val(dismantle_time);
    }

}

function shenghe_equip(){

    if(return_shenghe()===0){
        document.getElementById("tanchuang_shenghe").innerHTML="该sn可用"
        $('#tanchuang_shenghe').css('color','green');

    }else {
        document.getElementById("tanchuang_shenghe").innerHTML="该sn已存在"
        $('#tanchuang_shenghe').css('color','red');
    }

}

function return_shenghe(){
    const sn = $('#tanchuang_sn2').val();
    const tb = document.getElementById("equip_table");  //根据id找到这个表格
    const rows = tb.rows;               //取得这个table下的所有行
    for (let i = 0; i < rows.length; i++)//循环遍历所有的tr行
    {
        if (rows[i].cells[0].innerText === sn) {
            // $('#tanchuang_shenghe').innerHTML="该sn已存在！";
            return 1;
        }
    }
    return 0;

}


function shenghe_biaodan() {

    if(return_shenghe()==1){
        alert("您输入的表项中有不符要求的！请修改后提交！");
        return;
    }else {
        var form=document.getElementById("form_add");
        form.submit();
    }

}