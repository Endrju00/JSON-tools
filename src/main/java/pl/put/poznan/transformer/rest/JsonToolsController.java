package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

import java.util.Arrays;

@RestController
@RequestMapping("/{text}")
public class JsonToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JsonToolsController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        Json json = new JsonMinifierDecorator ( new JsonData(text));

        logger.debug(json.getData());

        return json.getData();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String jsonRequest) {
        String result;

        Json json = new JsonMinifierDecorator (new JsonValidatorDecorator (new JsonData(jsonRequest)));

        logger.debug(json.getData());

        return json.getData();
    }
}


