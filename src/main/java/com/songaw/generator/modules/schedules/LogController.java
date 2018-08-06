package com.songaw.generator.modules.schedules;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/6/14 9:15
 */

@RestController("/v1/log")

public class LogController {
    Logger log = LoggerFactory.getLogger(LogController.class);
    /*@Scheduled(cron = "0/4 * * * * *")
    public void test(){
        log.warn("执行日志"+ DateUtil.formatAsDatetime(new Date()));
    }*/
    @RequestMapping(path = "/test",method = RequestMethod.GET)
   // @Scheduled(cron = "0/5 * * * * *")
    public void test(){
       // Path path =Paths.get("D://");
       // test2(path.toFile());
        log.warn("执行日志.....");
    }
  /*  private void test2(  File file){
        log.warn(file.getAbsoluteFile().toString());
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                test2(file2);
            }
        }
    }*/
}
