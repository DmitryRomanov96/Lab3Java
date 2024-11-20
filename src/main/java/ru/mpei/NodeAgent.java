package ru.mpei;

import jade.core.Agent;
import ru.mpei.Bechaviours.MyParallelBehaviour;
import ru.mpei.Bechaviours.SimpleNodeBehaviour;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;


public class NodeAgent extends Agent {

    private String NodeAgentID;
    private boolean waitForAnswers = false;
    private boolean initiator;
    private boolean soughAfter;
    private List<NeighboursCfg> neighboursList;
    private List<SimpleAnswer> answers;

    @Override
    protected void setup() {
        String xmlFileName = (String) getArguments()[0];
        String path = "src/main/resources/"+xmlFileName;
        try {
            AgentCFG agentCFG = XMLParseHelper.deserialize(path, AgentCFG.class).get();
            this.NodeAgentID = agentCFG.getNodeAgentId();
            this.initiator = agentCFG.getInitiator();
            this.soughAfter = agentCFG.getSoughAfter();
            this.neighboursList = agentCFG.getNeigboursList();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        if (initiator){
            answers = new ArrayList<>();
            this.addBehaviour(new MyParallelBehaviour(this));
        }
        else {
            this.addBehaviour(new SimpleNodeBehaviour(this, neighboursList, soughAfter));
        }
    }

    public List<NeighboursCfg> getNeighboursList() {
        return neighboursList;
    }

    public void addAnswers(SimpleAnswer answer) {
        this.answers.add(answer);
    }

    public List<SimpleAnswer> getAnswers() {
        return answers;
    }

    public boolean isWaitForAnswers() {
        return waitForAnswers;
    }

    public void setWaitForAnswers(boolean waitForAnswers) {
        this.waitForAnswers = waitForAnswers;
    }
}
