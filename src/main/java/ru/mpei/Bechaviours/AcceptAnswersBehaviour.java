package ru.mpei.Bechaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.mpei.NodeAgent;
import ru.mpei.SimpleAnswer;

public class AcceptAnswersBehaviour extends Behaviour {

    private ACLMessage msg;
    private MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
    private NodeAgent myOwnAgent;

    public AcceptAnswersBehaviour(NodeAgent myOwnAgent) {
        this.myOwnAgent = myOwnAgent;
    }

    @Override
    public void action() {
        msg = myOwnAgent.receive(template);
        if (msg != null){
            if (msg.getProtocol().equals("<--")){
                myOwnAgent.addAnswers(new SimpleAnswer(msg.getContent()));
            }
        }
        else {block();}
    }

    @Override
    public boolean done() {
        return myOwnAgent.isWaitForAnswers();
    }
}
