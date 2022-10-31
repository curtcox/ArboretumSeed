package com.curtcox.arboretumseed;

import java.util.*;

final class StateTracker {

    final States states = new States();
    final List<MutationChoices> fields;

    StateTracker(List<MutationChoices> mutations) {
        this.fields = mutations;
    }

    public static <T> StateTracker of(List<MutationChoices<T, ?>> mutations) {
        return new StateTracker(cast(mutations));
    }

    private static <T> List<MutationChoices> cast(List<MutationChoices<T, ?>> mutations) {
        List x = mutations;
        return x;
    }

    Mutation next() {
        for (MutationChoices choices : fields) {
            Mutation novel = check(choices);
            if (novel!=null) {
                return novel;
            }
        }
        return null;
    }

    Mutation check(MutationChoices choices) {
        return states.findUnusedChoice(choices);
    }

}
