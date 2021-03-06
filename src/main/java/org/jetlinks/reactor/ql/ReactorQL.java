package org.jetlinks.reactor.ql;

import org.jetlinks.reactor.ql.feature.Feature;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.function.Function;

/**
 * <pre>
 *
 *   ReactorQL ql = ReactorQL
 *                  .builder()
 *                  .sql("select _id id,_name name from userFlux where age > 10")
 *                  .build();
 *
 *    ql.start(userFlux)
 *      .subscribe(map-> {
 *
 *      });
 *
 *
 * </pre>
 */
public interface ReactorQL {


    Flux<ReactorQLRecord> start(ReactorQLContext context);

    Flux<Map<String,Object>> start(Function<String, Publisher<?>> streamSupplier);

    default Flux<Map<String,Object>> start(Flux<?> flux) {
        return start((r) -> flux);
    }

    static Builder builder() {
        return new DefaultReactorQlBuilder();
    }

    interface Builder {

        Builder sql(String... sql);

        Builder feature(Feature... function);

        ReactorQL build();
    }

}
