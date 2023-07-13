package com.epam.spring.controller;

import com.epam.spring.model.Event;
import com.epam.spring.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "api/v1/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventServiceController {

    @Autowired
    private EventService eventService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Event")
    public void createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Event by id")
    public Event updateEvent(@PathVariable int id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Event by id")
    public Event getEvent(@PathVariable("id") int eventId) {
        return eventService.getEvent(eventId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Event by id")
    public void deleteEvent(@PathVariable int eventId) {
        eventService.deleteEvent(eventId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get All Events")
    public Collection<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(value = "/withTitle", params = "title", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiParam(name = "title", required = true)
    @ApiOperation(value = "Find Events by title")
    public Collection<Event> getAllEventsByTitle(@RequestParam("title") final String title) {
        return eventService.getAllEventsByTitle(title);
    }
}