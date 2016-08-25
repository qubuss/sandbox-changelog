package pl.bazus.changelog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bazus.changelog.properties.ConnectionProperties;

import javax.annotation.Resource;

@RestController
public class ShowPropertiesController {
    @Resource
    private ConnectionProperties connectionProperties;

    @RequestMapping("/showProperties")
    public String show(){
        return connectionProperties.toString();
    }
}
