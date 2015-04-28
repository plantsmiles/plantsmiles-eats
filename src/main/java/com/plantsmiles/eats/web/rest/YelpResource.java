package com.plantsmiles.eats.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.plantsmiles.eats.domain.yelp.YelpSearchResult;
import com.plantsmiles.eats.service.YelpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/app")
public class YelpResource {

    private final Logger log = LoggerFactory.getLogger(YelpResource.class);

    @Inject
    private YelpService yelpService;

    @RequestMapping(value = "/rest/yelp/{term}/{category}/{location}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public YelpSearchResult getBusinessByLocation(@PathVariable String term, @PathVariable String category,
                                                  @PathVariable String location, HttpServletResponse response) {
        log.debug("REST request to search by location: {},{},{}", term, category, location);
        return yelpService.searchByLocation(term, category, location);
    }

    @RequestMapping(value = "/rest/yelp",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public YelpSearchResult getBusinessByLocation(@RequestParam(value = "term") String term,
                                                  @RequestParam(value = "category") String category,
                                                  @RequestParam(value = "lat") String lat,
                                                  @RequestParam(value = "lon") String lon) {
        log.debug("REST request to search by latitude longitude: {},{},{},{}", term, category, lat, lon);
        return yelpService.searchByLatitudeLongitude(term, category, lat, lon);
    }
}
