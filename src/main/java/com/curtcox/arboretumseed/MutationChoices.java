package com.curtcox.arboretumseed;

import java.util.*;

import static com.curtcox.arboretumseed.debug.Debug.debug;
import static java.util.Collections.*;

/**
 * A method, plus a list of values it can be given.
 */
final class MutationChoices<T,O> {
// TODO FIXME fix equals/hashcode

    final Setter<T,O> method;
    final O[] values;
    final Iterable<O> producer;

    private MutationChoices(Setter<T, O> method, O[] values, Iterable<O> producer) {
        this.method = method;
        this.values = values;
        this.producer = producer;
    }

    static <T,O> MutationChoices<T,O> fromValues(Setter<T, O> method, O... values) {
        return new MutationChoices(method,values,EMPTY_LIST);
    }

    static <T,O> MutationChoices<T,O> fromProducer(Setter<T, O> method, Iterable<O> valueProducer) {
        return new MutationChoices(method,null,valueProducer);
    }

    Iterable<Mutation> mutations() {
        return debug("MutationChoices",values==null ? fromProducer() : fromValues());
    }

    Iterable<Mutation> fromValues() {
        List copies = new ArrayList<>();
        for (O value : values) copies.add(new Mutation<>(method,value));
        return copies;
    }

    Iterable<Mutation> fromProducer() {
        Iterator<O> values = producer.iterator();
        return () -> debug("MutationChoices from Producer",new Iterator<Mutation>() {
            @Override
            public boolean hasNext() {
                return values.hasNext();
            }

            @Override
            public Mutation next() {
                return new Mutation(method,values.next());
            }
        });

    }

    // Null means unknown
    Long count() {
        return values==null ? null : (long) values.length;
    }
}
