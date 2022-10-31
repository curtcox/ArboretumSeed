package com.curtcox.arboretumseed;

import java.util.*;

import static java.util.Collections.*;

final class State {

    final Map<MutationChoices,Object> values;

    State(Map<MutationChoices, Object> values) {
        this.values = values;
    }

    public static State empty() {
        return new State(EMPTY_MAP);
    }

    public State apply(MutationChoices choices, Mutation mutation) {
        Map valuesPlusChoices = new HashMap(values);
        valuesPlusChoices.put(choices,mutation);
        return new State(valuesPlusChoices);
    }

    @Override
    public boolean equals(Object o) {
        State that = (State) o;
        return values.equals(that.values);
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public String toString() {
        return "state : " + values;
    }
}
