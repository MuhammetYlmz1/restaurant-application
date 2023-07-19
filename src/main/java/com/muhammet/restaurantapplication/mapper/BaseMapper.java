package com.muhammet.restaurantapplication.mapper;

public interface BaseMapper<Source, Destination> {
    Destination map(Source source);
}
