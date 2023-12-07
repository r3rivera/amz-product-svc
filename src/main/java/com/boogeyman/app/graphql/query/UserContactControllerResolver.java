package com.boogeyman.app.graphql.query;

import com.boogeyman.app.graphql.models.ContactInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class UserContactControllerResolver {


    @SchemaMapping
    public String category(ContactInfo info){
        log.info("Category field was triggered for {} type", info.getCategory());
        return info.getCategory().toString();
    }

    @SchemaMapping
    public String contactValue(ContactInfo info){
        log.info("ContactValue field was triggered for {} type", info.getContactValue());
        return info.getContactValue();
    }

}
