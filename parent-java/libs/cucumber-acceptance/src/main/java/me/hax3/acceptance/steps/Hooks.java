package me.hax3.acceptance.steps;


import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import me.hax3.ITCucumber;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITCucumber.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class Hooks {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private List<GenericHolder> holders = emptyList();

    @Before
    public void setup() {
        log.info("Scenario Start.");
    }

    @After
    public void tearDown(Scenario scenario) {
        log.info("Scenario End.");
        if (scenario.isFailed()) {
            holders.forEach(holder -> log.error(holder.toString()));
        }
    }
}
