package com.graduate.towercranewaring;

import com.graduate.towercranewaring.csq.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
class TowercranewaringApplicationTests {

    @Test
    void contextLoads() {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间

        sdf.applyPattern("yyyy/MM/dd/HH:mm:ss");// a为am/pm的标记

        Date date=new Date();// 获取当前时间
        String time= sdf.format(date);
        System.out.println(time);// 输出已经格式化的现在时间(24小时制)
    }


    @Test
    void test1() throws IOException {
        String data=FileUtils.ImageToBase64ByLocal("E:\\Programming Related\\IdeaProjects\\" +
                "graduate_project\\src\\main\\resources\\static\\imgs\\driver_head\\d_001_49.9.jpg");
        System.out.println(data);
    }
}
