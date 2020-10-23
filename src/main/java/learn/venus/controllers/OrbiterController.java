package learn.venus.controllers;

import learn.venus.data.DataAccessException;
import learn.venus.domain.OrbiterResult;
import learn.venus.domain.OrbiterService;
import learn.venus.domain.ResultType;
import learn.venus.models.Orbiter;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://initial-domain.com"})
@RequestMapping("/pets")

//@RestController             // 1. Spring DI and MVC
//@RequestMapping("/orbiters") // 2. Base URL
public class OrbiterController {
    private final OrbiterService service;

    // 3. Auto-inject PetService
    public OrbiterController(OrbiterService service) {
        this.service = service;
    }

    @GetMapping
    public List<Orbiter> findAll() throws DataAccessException {
        return service.findAll();
    }

    @GetMapping("/{orbiterId}")
    public ResponseEntity<Orbiter> findById(@PathVariable int orbiterId) throws DataAccessException {
        Orbiter orbiter = service.findById(orbiterId);

        if (orbiter == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orbiter, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Orbiter> add(@RequestBody Orbiter orbiter) throws DataAccessException {
        OrbiterResult<Orbiter> result = service.add(orbiter);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }


    @PutMapping("/{orbiterId}")
    public ResponseEntity<Void> update(@PathVariable int orbiterId, @RequestBody Orbiter orbiter) throws DataAccessException {

        // id conflict. stop immediately.
        if (orbiterId != orbiter.getOrbiterId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 4. ResultType -> HttpStatus
        OrbiterResult<Orbiter> result = service.update(orbiter);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{orbiterId}")
    public ResponseEntity<Void> delete(@PathVariable int orbiterId) throws DataAccessException {
        if (service.deleteById(orbiterId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/post")
    public ResponseEntity<Orbiter> doPost(@RequestBody Orbiter orbiter) {
        if (!isValid(orbiter)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orbiter, HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity<Void> doPut(@RequestBody Orbiter orbiter) {
        if (orbiter.getOrbiterId() != 15) {
            return ResponseEntity.notFound().build();
        } else if (!isValid(orbiter)) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    private boolean isValid(Orbiter orbiter) {
        return orbiter != null
                && orbiter.getName() != null
                && orbiter.getName().trim().length() > 0;
    }
}

//DONE 2
