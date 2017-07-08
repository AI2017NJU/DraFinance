package strategyTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import strategy.runner.impl.GapRunner;

/**
 * Created by stonezhang on 2017/7/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mvc.xml"})
public class GapRunnerTest {
    @Autowired
    private GapRunner gapRunner;

    @Test
    public void testRunner() {
//        gapRunner.runBacktest(10000, "2016-01-01", "2016-07-01");
    }
}
