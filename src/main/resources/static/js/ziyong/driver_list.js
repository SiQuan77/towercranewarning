function bianji_driver(id){
    $('#bianjiModalId').modal('show');

    var tb = document.getElementById("driver_table");  //根据id找到这个表格
    var rows = tb.rows;               //取得这个table下的所有行

    var driver_id=id;
    var driver_name;
    var driver_age;
    var driver_gender;
    var driver_id_card_num;
    var driver_tel_num;

    for (var i = 0; i < rows.length; i++)//循环遍历所有的tr行
    {
        for (var j = 0; j < rows[i].cells.length; j++)//取得第几行下面的td个数，再次循环遍历该行下面的td元素
        {
            // var cell = rows[i].cells[j];//获取某行下面的某个td元素
            // console.log("第" + (i + 1) + "行第" + (j + 1) + "格的数字是" + cell.innerHTML);//cell.innerHTML获取元素里头的值
            if (rows[i].cells[1].innerText === id) {
                driver_name = rows[i].cells[2].innerText;
                driver_age = rows[i].cells[3].innerText;
                driver_gender = rows[i].cells[4].innerText;
                driver_id_card_num = rows[i].cells[5].innerText;
                driver_tel_num = rows[i].cells[6].innerText;
            }
        }

    }

    $("#tanchaung_id").val(driver_id);
    $("#tanchuang_name").val(driver_name);
    $("#tanchuang_age").val(driver_age);
    $("#tanchuang_gender").val(driver_gender);
    $("#tanchuang_id_card").val(driver_id_card_num);
    $("#tanchuang_tel").val(driver_tel_num);



}

function add_driver() {
    $('#addjiModalId').modal('show');
}


function delete_driver(id) {
    var msg="您真的要删除id号为"+id+"的司机吗？\n\n请确认！"

    if(confirm(msg)===true){
        $.post(
            "/driver/delete",
            {"driver_id":id},
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