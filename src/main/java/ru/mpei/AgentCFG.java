package ru.mpei;


import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "cfg")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentCFG {

    @XmlElementWrapper(name = "Neighbours")
    @XmlElement(name = "NewNeighbour")
    private List<NeighboursCfg> neigboursList;


    @XmlElement
    private String initiator;
    @XmlElement
    private String soughAfter;

    @XmlElement
    private String NodeAgentId;



    public String getNodeAgentId() {
        return NodeAgentId;
    }


    public List<NeighboursCfg> getNeigboursList() {
        return neigboursList;
    }

    public boolean getInitiator() {
        return Boolean.parseBoolean(initiator);
    }

    public boolean getSoughAfter() {
        return Boolean.parseBoolean(soughAfter);
    }
}
