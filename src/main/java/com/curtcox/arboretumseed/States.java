package com.curtcox.arboretumseed;

import java.util.*;

final class States {

    State state = State.empty();
    final Set<State> priorStates = new HashSet<>();

    States() {
        priorStates.add(state);
    }

    Mutation findUnusedChoice(MutationChoices choices) {
        for (Object o : choices.mutations()) {
            Mutation mutation = (Mutation) o;
            State candidate = state.apply(choices,mutation);
            if (!priorStates.contains(candidate)) {
                state = candidate;
                priorStates.add(candidate);
                return mutation;
            }
        }
        return null;
    }

}
