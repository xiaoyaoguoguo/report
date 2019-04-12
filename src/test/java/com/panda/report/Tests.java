package com.panda.report;

import com.panda.common.utils.Md5Utils;
import org.junit.Test;

/**
 * @author jamie
 * @ClassName: Tests
 * @Description:
 * @data 2019-04-11 15:55
 **/
public class Tests {

    @Test
    public void test1(){
        String s = Md5Utils.encode("admin123");
        System.out.println(s);

    }

}
