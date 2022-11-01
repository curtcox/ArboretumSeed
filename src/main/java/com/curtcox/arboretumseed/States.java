package com.curtcox.arboretumseed;

import java.util.*;

final class States {

    State state = State.empty();
    final Set<State> priorStates = new HashSet<>();

    States() {
        priorStates.add(state);
    }

    Mutation findUnusedChoice(MutationChoices field) {
        for (Object o : field.mutations()) {
            Mutation mutation = (Mutation) o;
            if (mutationProducesNewState(field,mutation)) {
                return mutation;
            }
        }
        return null;
    }

    boolean mutationProducesNewState(MutationChoices field, Mutation mutation) {
        State candidate = state.apply(field,mutation);
        if (priorStates.contains(candidate)) {
            return false;
        }
        state = candidate;
        priorStates.add(candidate);
        return true;
    }

}
