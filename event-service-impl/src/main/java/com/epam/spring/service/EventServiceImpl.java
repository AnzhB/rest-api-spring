package com.epam.spring.service;

import com.epam.spring.exception.NotFoundException;
import com.epam.spring.model.Event;
import com.epam.spring.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
public class EventServiceImpl implements EventService {
    private final ModelMapper mapper;

    @Autowired
    private EventRepository repository;

    public EventServiceImpl(ModelMapper mapper, EventRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void createEvent(Event event) {
        repository.save(event);
    }

    @Override
    public Event updateEvent(int id, Event event) {
        Event updatedEvent = repository.findById(id).orElseThrow(()
                -> new NotFoundException("Event with id " + id + " was not found"));
        mapper.map(event, updatedEvent);
        return repository.save(updatedEvent);
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