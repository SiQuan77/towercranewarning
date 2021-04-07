

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
