package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;


public class PublisherConverter implements Converter<String, Publisher> {
    @Autowired
    PublisherDao publisherDao;

    public Publisher convert(String value){
        try {
            long id = Long.parseLong(value);
            return publisherDao.findOneById(id);
        }
        catch (Exception e) {
            return null;
        }
    }
}
