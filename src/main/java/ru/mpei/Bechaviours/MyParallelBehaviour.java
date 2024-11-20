package ru.mpei.Bechaviours;

import jade.core.behaviours.ParallelBehaviour;
import ru.mpei.NodeAgent;

public class MyParallelBehaviour extends ParallelBehaviour {
    public MyParallelBehaviour(NodeAgent a) {
        System.out.println(a.getLocalName()+" entered parallel behaviour");
        addSubBehaviour(new InitiatorBehaviour(a.getNeighboursList()));
        addSubBehaviour(new AcceptAnswersBehaviour(a));
        addSubBehaviour(new WaitForAnswers(a, 10_000));
    }
}
