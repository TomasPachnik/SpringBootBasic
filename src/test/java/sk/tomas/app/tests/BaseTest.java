package sk.tomas.app.tests;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextConfiguration("classpath:application-context-test.xml")
public abstract class BaseTest {
}
