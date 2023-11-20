package br.com.cassianojuan.todolist.todolist.api.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping("/")
public class RootEntryPointController {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public RootEntryPointModel root(){
    RootEntryPointModel rootEntryPointModel = new RootEntryPointModel();

    rootEntryPointModel.add(linkTo(methodOn(TodoController.class)
    .list()).withRel("todos"));

    return rootEntryPointModel;
  }

  private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{}
}
