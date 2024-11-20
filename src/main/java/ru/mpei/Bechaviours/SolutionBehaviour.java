package ru.mpei.Bechaviours;

import jade.core.behaviours.OneShotBehaviour;
import ru.mpei.NodeAgent;
import ru.mpei.SimpleAnswer;

import java.util.ArrayList;
import java.util.List;

public class SolutionBehaviour extends OneShotBehaviour {

    private NodeAgent myOwnAgent;

    public SolutionBehaviour(NodeAgent a) {
        myOwnAgent = a;
    }

    @Override
    public void action() {
        List<SimpleAnswer> finalAnswers = new ArrayList<>();
        for (SimpleAnswer answer: myOwnAgent.getAnswers()){
            if (answer.isFound()){
                finalAnswers.add(answer);
            }
        }
        if (finalAnswers.isEmpty()){
            System.out.println("No solution was found");
        }
        else {
            SimpleAnswer solution = finalAnswers.get(0);
            for (SimpleAnswer answer: finalAnswers){
                if (answer.getPathLength() < solution.getPathLength()){
                    solution = answer;
                }
            }
            solution.print();
        }
    }

}
