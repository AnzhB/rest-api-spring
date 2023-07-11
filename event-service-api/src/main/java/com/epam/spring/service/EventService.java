package com.epam.spring.service;

import com.epam.spring.model.Event;

import java.util.List;

public interface EventService {

    void createEvent(Event event);
    void updateEvent(Event event);
    Event getEvent(int eventId);
    void deleteEvent(int eventId);
    List<Event> getAllEvents();
    List<Event> getAllEventsByTitle(String title);
}