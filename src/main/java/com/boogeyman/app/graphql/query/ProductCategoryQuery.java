package com.boogeyman.app.graphql.query;

import com.boogeyman.app.graphql.models.ProductCategoryResponse;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductCategoryQuery implements GraphQLQueryResolver {

    public DataFetcherResult<ProductCategoryResponse> product(long id){
        final ProductCategoryResponse message = new ProductCategoryResponse();
        message.setId(id);
        log.info("Done creating the root response!");

        final DataFetcherResult.Builder<ProductCategoryResponse> fetcherResult = DataFetcherResult.newResult();
        fetcherResult.data(message);
        
        return fetcherResult.build();
    }

}
