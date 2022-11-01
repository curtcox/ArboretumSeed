package com.curtcox.arboretumseed;

import java.util.*;

final class StateTracker {

    MutationChoices last;
    final States states = new States();
    final List<MutationChoices> fields;

    StateTracker(List<MutationChoices> mutations) {
        this.fields = mutations;
    }

    public static <T> StateTracker of(List<MutationChoices<T, ?>> mutations) {
        return new StateTracker(cast(mutations));
    }

    private static <T> List<MutationChoices> cast(List<MutationChoices<T, ?>> mutations) {
        return (List) mutations;
    }

    Mutation next() {
        for (MutationChoices choices : fieldsStartingAt(last)) {
            Mutation novel = check(choices);
            if (novel!=null) {
                last = choices;
                return novel;
            }
        }
        return null;
    }

    private List<MutationChoices> fieldsStartingAt(MutationChoices field) {
        if (field==null) {
            return fields;
        }
        int start = fields.indexOf(field);
        int size = fields.size();
        List<MutationChoices> shifted = new ArrayList<>(size);
        for (int i=0; i<size; i++) {
            shifted.add(fields.get((start + 1 + i) % size));
        }
        return shifted;
    }

    Mutation check(MutationChoices choices) {
        return states.findUnusedChoice(choices);
    }

}
