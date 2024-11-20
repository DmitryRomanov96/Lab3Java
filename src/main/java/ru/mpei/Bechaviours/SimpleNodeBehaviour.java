package ru.mpei.Bechaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.mpei.NeighboursCfg;
import ru.mpei.NodeAgent;

import java.util.Arrays;
import java.util.List;

public class SimpleNodeBehaviour extends Behaviour {

    private ACLMessage msg;
    private MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
    private List<NeighboursCfg> neighboursCfgList;
    private boolean isFinal;

    public SimpleNodeBehaviour(NodeAgent a, List<NeighboursCfg> neighboursCfgList, boolean isFinal) {
        super(a);
        this.myAgent = a;
        this.neighboursCfgList = neighboursCfgList;
        this.isFinal = isFinal;
    }

    @Override
    public void action() {
        this.msg = this.myAgent.receive(this.template);
        if (this.msg != null) {
//            System.out.println(myAgent.getLocalName()+" got a message from "+msg.getSender().getLocalName());
            if (this.msg.getProtocol().equals("-->")) {
                this.actionOfFinal();
                this.actionForward();
            } else if (this.msg.getProtocol().equals("<--")) {
                this.actionBackward();
            } else {
                System.err.println(myAgent.getLocalName() + " got unknown message");
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }

    private void actionForward() {
        boolean flg = false;
        String content = this.msg.getContent();
        String[] messageParts = content.split("/");
        String[] receivers = messageParts[0].split("-");
        for (NeighboursCfg neighbour : neighboursCfgList) {
            if (!Arrays.asList(receivers).contains("Agent" + neighbour.getAgentID())) {
                flg = true;
                String newContent = messageParts[0] + "-" + myAgent.getLocalName() + "/" +
                        messageParts[1] + "-" + neighbour.getPathWeight();
                ACLMessage reply = new ACLMessage();
                reply.setPerformative(ACLMessage.REQUEST);
                reply.setProtocol("-->");
                reply.setContent(newContent);
                reply.addReceiver(new AID("Agent" + neighbour.getAgentID(), false));
                myAgent.send(reply);
            }
        }
        if (!flg) {
            ACLMessage reply = new ACLMessage();
            reply.setPerformative(ACLMessage.REQUEST);
            reply.setProtocol("<--");
            reply.setContent(messageParts[0] + "-" + myAgent.getLocalName() + "/" + messageParts[1] + "/" + isFinal);
            reply.addReceiver(new AID(receivers[receivers.length - 1], false));
            myAgent.send(reply);
        }
    }

    private void actionBackward() {
        String content = this.msg.getContent();
        String[] messageParts = content.split("/");
        String[] receivers = messageParts[0].split("-");
        ACLMessage reply = new ACLMessage();
        reply.setPerformative(ACLMessage.REQUEST);
        reply.setProtocol("<--");
        reply.setContent(msg.getContent());
        for (int i = 0; i < receivers.length; i++) {
            if (myAgent.getLocalName().equals(receivers[i])) {
                reply.addReceiver(new AID(receivers[i - 1], false));
                break;
            }
        }
        myAgent.send(reply);
    }

    private void actionOfFinal() {
        if (isFinal) {
            String content = this.msg.getContent();
            String[] messageParts = content.split("/");
            String[] receivers = messageParts[0].split("-");
            ACLMessage reply = new ACLMessage();
            reply.setPerformative(ACLMessage.REQUEST);
            reply.setProtocol("<--");
            reply.setContent(messageParts[0]+"-"+myAgent.getLocalName()+"/"+messageParts[1]+"/"+isFinal);
            reply.addReceiver(new AID(receivers[receivers.length - 1], false));
            myAgent.send(reply);
        }
    }

}
