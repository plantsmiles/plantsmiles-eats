package com.plantsmiles.eats.service;

import com.plantsmiles.eats.Application;
import com.plantsmiles.eats.domain.yelp.Business;
import com.plantsmiles.eats.domain.yelp.YelpSearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class YelpServiceTest {

    @Inject
    private YelpService yelpService;

    @Test
    public void testGetResultsBack() {
        YelpSearchResult yelpSearchResult = yelpService.searchByLocation("food","vegan", "Denver, CO");

        for (Business business : yelpSearchResult.getBusinesses()) {
            System.out.println(business.getName());
        }
    }
}
