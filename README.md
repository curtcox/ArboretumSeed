# ArboretumSeed
For generating Java object trees.

# Why?
This summer (2022), I joined a team that wasn't using Lombok on a Java code base that was screaming for it.
The code had a bunch of classes that were either POJOs or close, but that was largely obscured by all the
getter/setter/constructor/hashcode/equals boilerplate. Switching to Lombok made everything much more readable.

Unfortunately, switching to Lombok also introduced bugs. Some existing classes violated the equals/hashcode
contact in a way that only surfaced once Lombok had been partially introduced. Once detected, the problem was easily
fixed. So, I started looking for tools that would detect hashcode/equals contract violations. It's possible, to write
the relevant tests by hand, but difficult and tedious enough that people avoid doing so.

Searching for relevant tools turns up some interesting near misses.
- **automated bean testing** There are tools like [MeanBean](https://github.com/meanbeanlib/meanbean) that specifically test for equals and hascode violations.
- **property based testing** There are tools like [jqwik](https://jqwik.net/property-based-testing.html) for this sort of thing.

Unfortunately, neither of these will detect the equals/hashcode violations I was looking for out-of-the-box.
Property based testing tools have an added disadvantage. They interfere with existing ordinary JUnit tests.

