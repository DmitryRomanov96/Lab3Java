package ru.mpei.Bechaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import ru.mpei.NeighboursCfg;

import java.util.List;

public class InitiatorBehaviour extends OneShotBehaviour {
    private List<NeighboursCfg> neighboursCfgList;

    public InitiatorBehaviour(List<NeighboursCfg> neighboursCfgList) {
        this.neighboursCfgList = neighboursCfgList;
    }

    @Override
    public void action() {
        for (NeighboursCfg neighbour: neighboursCfgList){
            ACLMessage msg = new ACLMessage();
            msg.setPerformative(ACLMessage.REQUEST);
            msg.setProtocol("-->");
            msg.setContent(myAgent.getLocalName()+"/"+neighbour.getPathWeight());
            msg.addReceiver(new AID("Agent"+neighbour.getAgentID(), false));
            myAgent.send(msg);
        }
    }
}
