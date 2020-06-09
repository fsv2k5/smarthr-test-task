package com.smarthr.employeedb.controller;

import com.smarthr.employeedb.domain.BaseEntity;
import com.smarthr.employeedb.converter.EntityMapper;
import com.smarthr.employeedb.service.EntityService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Getter
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class EntityController<Entity extends BaseEntity, DTO> {
    EntityService<Entity> service;
    EntityMapper<Entity, DTO> toDTOMapper;
    EntityMapper<DTO, Entity> fromDTOMapper;

    @ResponseBody
    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<?>> getAll() {
        return new ResponseEntity<>(service.getAll(1, 100).stream()
                .map(toDTOMapper)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = {"", "/"}, params = {"page", "size"})
    public ResponseEntity<List<?>> getAllPaged(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size) {
        return new ResponseEntity<>(service.getAll(page, size).stream()
                .map(toDTOMapper)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(path = {"ids", "ids/"})
    public List<DTO> getAllByIds(@RequestBody List<UUID> ids) {
        return toDTOMapper.apply(service.getAllByIds(ids));
    }

    @ResponseBody
    @PutMapping(path = {"", "/"})
    public List<DTO> saveAll(@RequestBody List<DTO> dtoList) {
        return toDTOMapper.apply(service.save(fromDTOMapper.apply(dtoList)));
    }

    @ResponseBody
    @PostMapping(path = {"", "/"})
    @ResponseStatus(value = HttpStatus.OK)
    public DTO save(@RequestBody DTO dto) {
        return toDTOMapper.apply(service.save(fromDTOMapper.apply(dto)));
    }

    @ResponseBody
    @GetMapping("/{id}")
    public DTO getById(@PathVariable UUID id) {
        Entity out = service.getById(id);
        return ofNullable(out).map(e -> toDTOMapper.apply(e)).orElseThrow(RuntimeException::new);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}