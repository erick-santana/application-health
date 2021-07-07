package com.imalive.api.Controller;

import com.imalive.api.Model.Base;
import com.imalive.api.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("bases")
@RequiredArgsConstructor
public class BaseContoller {
    private final BaseService baseService;

    @GetMapping
    public ResponseEntity<List<Base>> listAll() {
        return ResponseEntity.ok(baseService.listAll());
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Base> findByBucketName(@RequestParam String bucketName) {
        return ResponseEntity.ok(baseService.findByBucketName(bucketName));
    }

    @PostMapping
    public ResponseEntity<Base> save(@RequestBody @Valid Base base) {
        return new ResponseEntity<>(baseService.save(base), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{bucketName}")
    public ResponseEntity<Void> delete(@PathVariable String bucketName) {
        baseService.delete(bucketName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
