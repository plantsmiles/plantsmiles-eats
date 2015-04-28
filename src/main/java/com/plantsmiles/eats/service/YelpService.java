package com.plantsmiles.eats.service;

import com.plantsmiles.eats.domain.TwoStepOAuth;
import com.plantsmiles.eats.domain.yelp.YelpSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

@Service
public class YelpService {
    private final Logger log = LoggerFactory.getLogger(YelpService.class);

    private static final String API_HOST = "api.yelp.com";
    private static final String DEFAULT_TERM = "food";
    private static final String DEFAULT_LOCATION = "Denver, C0";
    private static final int SEARCH_LIMIT = 20;
    private static final String SEARCH_PATH = "/v2/search";
    private static final String BUSINESS_PATH = "/v2/business";

    //TODO: FILL THESE IN TO PERFORM YELP QUERYS
    private static final String CONSUMER_KEY = "";
    private static final String CONSUMER_SECRET = "";
    private static final String TOKEN = "";
    private static final String TOKEN_SECRET = "";

    OAuthService service;
    Token accessToken;

    public YelpService() {
        this.service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(CONSUMER_KEY)
                .apiSecret(CONSUMER_SECRET).build();
        this.accessToken = new Token(TOKEN, TOKEN_SECRET);
    }

    public YelpSearchResult searchByLocation(final String term, final String category, final String location) {
        final OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("category_filter", category);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));

        final String searchResponseJSON = sendRequestAndGetResponse(request);

        return new Gson().fromJson(searchResponseJSON, YelpSearchResult.class);
    }

    public YelpSearchResult searchByLatitudeLongitude(final String term, final String category, final String latitude,
                                            final String longitude) {
        final OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("category_filter", category);
        request.addQuerystringParameter("ll", latitude + "," + longitude);
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));

        final String searchResponseJSON = sendRequestAndGetResponse(request);

        return new Gson().fromJson(searchResponseJSON, YelpSearchResult.class);
    }

    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + path);
        return request;
    }

    private String sendRequestAndGetResponse(OAuthRequest request) {
        log.info("Querying " + request.getCompleteUrl() + " ...");
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }
}
