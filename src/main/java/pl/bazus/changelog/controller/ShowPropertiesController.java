package pl.bazus.changelog.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.properties.ConnectionProperties;

import javax.annotation.Resource;

@RestController
public class ShowPropertiesController {
    private final static Logger LOGGER = Logger.getLogger(ShowPropertiesController.class);

    @Resource
    private ConnectionProperties connectionProperties;

    @RequestMapping("/showProperties")
    public ConnectionProperties show() {
        LOGGER.info("propert");
        return connectionProperties;
    }
}
