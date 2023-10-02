package com.example.registertestauthorization.autogen;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.EventType;
import org.hibernate.generator.Generator;
import org.hibernate.id.IdentifierGenerator;

import java.util.EnumSet;
import java.util.stream.Stream;

import static org.apache.naming.SelectorContext.prefix;

public class MyGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object obj) {
        String query = "SELECT m.accountId FROM Account m";
        Stream<String> ids = session.createQuery(query,String.class).stream();
        Long max = ids.map(o->o.replace(prefix,"")).mapToLong(Long::parseLong).max().orElse(0L);
        return prefix + (String.format("%04d",max + 1));
    }
}
