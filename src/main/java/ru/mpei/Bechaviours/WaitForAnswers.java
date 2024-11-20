package ru.mpei.Bechaviours;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import ru.mpei.NodeAgent;

public class WaitForAnswers extends WakerBehaviour {

    private NodeAgent myOwnAgent;

    public WaitForAnswers(NodeAgent a, long timeout) {
        super(a, timeout);
        myOwnAgent = a;
    }

    @Override
    protected void onWake() {
        super.onWake();
        myOwnAgent.setWaitForAnswers(true);
        myOwnAgent.addBehaviour(new SolutionBehaviour(myOwnAgent));
    }
}
