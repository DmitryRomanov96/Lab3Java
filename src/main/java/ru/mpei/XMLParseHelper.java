package ru.mpei;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Optional;

public class XMLParseHelper {

    public static <T> Optional<T> deserialize(String path, Class<T> clazz) throws JAXBException {
        File f = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        System.out.println(f.getPath());
        T cfg = (T) unmarshaller.unmarshal(f);
        return Optional.ofNullable(cfg);
    }

}
