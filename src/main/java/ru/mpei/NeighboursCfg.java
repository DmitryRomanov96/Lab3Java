package ru.mpei;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class NeighboursCfg {
    @XmlElement
    private String AgentID;
    @XmlElement
    private String PathWeight;

    public String getAgentID() {
        return AgentID;
    }

    public String getPathWeight() {
        return PathWeight;
    }
}
