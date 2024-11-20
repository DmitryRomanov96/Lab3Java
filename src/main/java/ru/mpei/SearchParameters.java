package ru.mpei;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchParameters {

    @XmlAttribute
    private String initiator;
    @XmlAttribute
    private String soughAfter;


    public boolean getInitiator() {
        return Boolean.getBoolean(initiator);
    }

    public boolean getSoughAfter() {
        return Boolean.getBoolean(soughAfter);
    }
}
