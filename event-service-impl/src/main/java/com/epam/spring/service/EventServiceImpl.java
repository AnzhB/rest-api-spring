package com.epam.spring.service;

import com.epam.spring.exception.NotFoundException;
import com.epam.spring.model.Event;
import com.epam.spring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    public void createEvent(Event event) {
        repository.save(event);
    }

    @Override
    public void updateEvent(Event event) {
        getEvent(event.getId());
        repository.save(event);
    }

    @Override
    public void deleteEvent(int eventId) {
        repository.deleteById(eventId);
    }

    @Override
    public Event getEvent(int eventId) {
        return repository.findById(eventId).orElseThrow(() -> new NotFoundException("Even was not found"));
    }

    @Override
    public List<Event> getAllEvents() {
        return stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return repository.findAllByTitle(title);
    }
}