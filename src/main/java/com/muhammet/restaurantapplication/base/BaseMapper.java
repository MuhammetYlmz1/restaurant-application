package com.muhammet.restaurantapplication.base;

public interface BaseMapper<Source, Destination> {
    Destination map(Source source);
}
