package com.graduate.towercranewaring;

import com.graduate.towercranewaring.csq.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@SpringBootTest
class TowercranewaringApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void test1() throws IOException {
        String data=FileUtils.ImageToBase64ByLocal("E:\\Programming Related\\IdeaProjects\\" +
                "graduate_project\\src\\main\\resources\\static\\imgs\\driver_head\\d_001_49.9.jpg");
        System.out.println(data);
    }
}
