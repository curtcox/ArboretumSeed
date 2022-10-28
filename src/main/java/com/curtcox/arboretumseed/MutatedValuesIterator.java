package com.curtcox.arboretumseed;

import java.util.*;

/**
 * For producing mutated copies of an original value.
 */
final class MutatedValuesIterator<T> implements Iterator<T> {
    private final LinkedList<T> valuesToReturn = new LinkedList<>();
    private final Set<T> generated = new HashSet<>();
    private final List<MutationChoices<T,?>> mutations;
    private List<Iterator<Mutation>> iterators;
    private T current;
    private final Copier<T> copier;
    private int index;

    private MutatedValuesIterator(T initial, Copier<T> copier, List<MutationChoices<T,?>> mutations) {
        this.mutations = mutations;
        this.current = initial;
        this.copier = copier;
        valuesToReturn.add(initial);
        iterators = iterators(mutations);
    }

    static <T> MutatedValuesIterator<T> of(T initial, Copier<T> copier, List<MutationChoices<T,?>> mutations) {
        return new MutatedValuesIterator<>(initial,copier,mutations);
    }

    static <T> List<Iterator<Mutation>> iterators(List<MutationChoices<T,?>> mutations) {
        List<Iterator<Mutation>> iterators = new ArrayList<>();
        for (MutationChoices choices : mutations) {
            iterators.add(choices.mutations().iterator());
        }
        return iterators;
    }

    @Override public boolean hasNext() {
        return !valuesToReturn.isEmpty();
    }
    void addMoreValues() {
        refresh();
        if (index>=iterators.size()) index = 0;

        Iterator<Mutation> iterator = iterators.get(index);
        addValueFromIterator(iterator);
        if (!iterator.hasNext()) {
            iterators.remove(iterator);
            refresh();
        }
    }

    void refresh() {
        if (iterators.isEmpty()) {
            iterators = iterators(mutations);
        }
    }
    void addValueFromIterator(Iterator<Mutation> iterator) {
        if (!iterator.hasNext()) return;
        Mutation mutation = iterator.next();
        index++;
        T candidate = copier.copy(current);
        mutation.mutate(candidate);
        if (!generated.contains(candidate)) {
            current = candidate;
            valuesToReturn.add(current);
            generated.add(current);
        }
    }

    private int computed() {
        // FIXME TODO there is probably a better value to use here.
        return mutations.size() * 2;
    }
    @Override public T next() {
        if (valuesToReturn.isEmpty()) throw new IllegalStateException();
        T value = valuesToReturn.removeFirst();
        for (int i=0; i<computed() && valuesToReturn.isEmpty(); i++) {
            addMoreValues();
        }
        return value;
    }
}
