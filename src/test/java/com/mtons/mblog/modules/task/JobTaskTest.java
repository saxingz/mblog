package com.mtons.mblog.modules.task;

import com.mtons.mblog.BootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * post service test
 *
 * @author saxing 2019/4/5 17:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class JobTaskTest {

    @Autowired
    JobTask jobTask;

    @Test
    public void testA() {
        jobTask.testA();
        jobTask.del3DayAgoPic();
    }
}