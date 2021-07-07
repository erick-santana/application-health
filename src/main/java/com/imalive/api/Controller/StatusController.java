package com.imalive.api.Controller;

import com.imalive.api.Model.Base;
import com.imalive.api.Service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping(path = "/up")
    public ResponseEntity<List<Base>> listAllRunning() {
        return ResponseEntity.ok(statusService.listAllRunning());
    }

    @GetMapping(path = "/down")
    public ResponseEntity<List<Base>> listAllNotRunning() {
        return ResponseEntity.ok(statusService.listAllNotRunning());
    }
}
